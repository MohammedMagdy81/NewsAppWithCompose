package com.example.newsappcompose.domin.use_cases.appentry

import com.example.newsappcompose.domin.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}