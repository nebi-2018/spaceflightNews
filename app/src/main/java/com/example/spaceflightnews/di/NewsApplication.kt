package com.example.spaceflightnews.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * This is the main entry class for the integration of hilt dependency injection
 */
@HiltAndroidApp
class NewsApplication:Application()