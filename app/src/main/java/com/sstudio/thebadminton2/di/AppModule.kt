package com.sstudio.thebadminton2.di

import com.sstudio.thebadminton2.core.domain.usecase.TheBadmintonInteractor
import com.sstudio.thebadminton2.core.domain.usecase.TheBadmintonUseCase
import com.sstudio.thebadminton2.menu3.note.NoteViewModel
import com.sstudio.thebadminton2.menu3.video.VideoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TheBadmintonUseCase> { TheBadmintonInteractor(get()) }
}

val viewModelModule = module {
    viewModel { VideoViewModel(get()) }
    viewModel { NoteViewModel(get()) }
}