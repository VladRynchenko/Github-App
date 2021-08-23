package com.example.githubapp.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.ui.auth.LoginViewModel
import com.example.githubapp.ui.main.MainViewModel
import com.example.githubapp.ui.profile.ProfileViewModel
import com.example.githubapp.ui.repo.DetailViewModel
import com.example.githubapp.ui.repos.RepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProvideFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    internal abstract fun bindReposViewModel(viewModel: RepoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindRepoViewModel(viewModel: DetailViewModel): ViewModel

}