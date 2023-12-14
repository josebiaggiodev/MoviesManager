package br.edu.ifsp.aluno.moviesmanager.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var id: Int? = -1,
    var time: Long = INVALID_TIME,
    var name: String = "",
    var releaseYear: Int = 0,
    var producer: String = "",
    var duration: Int = 0,
    var isWatched: String = "",
    var review: String = "",
    var genre: String = ""
): Parcelable {
    companion object {
        const val INVALID_TIME = -1L
    }
}
