package com.example.common.util

interface EntityMapper<NetworkEntity, DomainModel> {
    fun mapFromRemoteEntity(entity: NetworkEntity): DomainModel
}