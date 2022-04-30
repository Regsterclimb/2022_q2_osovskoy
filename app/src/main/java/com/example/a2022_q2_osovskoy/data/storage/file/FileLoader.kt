package com.example.a2022_q2_osovskoy.data.storage.file

interface FileLoader {

    suspend fun load()

    class Base :FileLoader {

        override suspend fun load() {

        }
    }
}