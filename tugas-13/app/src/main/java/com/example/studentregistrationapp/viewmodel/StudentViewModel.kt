package com.example.studentregistrationapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentregistrationapp.data.Siswa
import com.example.studentregistrationapp.data.SiswaDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val dao: SiswaDao
) : ViewModel() {

    val siswaList = dao.getAllSiswa()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun tambahSiswa(nama: String, email: String) {
        viewModelScope.launch {
            dao.insertSiswa(
                Siswa(
                    nama = nama,
                    email = email
                )
            )
        }
    }

    fun hapusSiswa(siswa: Siswa) {
        viewModelScope.launch {
            dao.deleteSiswa(siswa)
        }
    }

    fun editSiswa(siswa: Siswa) {
        viewModelScope.launch {
            dao.updateSiswa(siswa)
        }
    }
}
