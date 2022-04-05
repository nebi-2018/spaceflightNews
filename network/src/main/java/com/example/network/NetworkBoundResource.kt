package com.example.network

import com.example.common.util.Resource
import kotlinx.coroutines.flow.*

/**
 * This is responsible retrieving sufficiently recent data from a local cache, or loading the
 * latest data from the network
 */
inline fun <ResultType,RequestType> networkBoundResource(
    crossinline query:()->Flow<ResultType>,
    crossinline fetch:suspend ()->RequestType,
    crossinline saveFetchResult:suspend (RequestType)->Unit,
    crossinline shouldFetch:(ResultType)->Boolean = {true}
) = flow {

    val data = query().first()
    val flow = if (shouldFetch(data)){
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        }catch (throwable:Throwable){
            query().map { Resource.Error(throwable, it) }
        }
    }else{
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}

