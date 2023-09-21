package com.example.newsappcompose.presentaion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.domin.use_cases.appentry.AppEntryUseCases
import com.example.newsappcompose.presentaion.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNav.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHome ->
            if (shouldStartFromHome)
                startDestination = Route.NewsNavigation.route
            else
                startDestination = Route.AppStartNav.route

            delay(3000)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}