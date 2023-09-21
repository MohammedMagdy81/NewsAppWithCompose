package com.example.newsappcompose.domin.use_cases.appentry

import com.example.newsappcompose.domin.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

     operator fun invoke() = localUserManager.readAppEntry()

}

