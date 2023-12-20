package com.example.movieapptmdb.feature.movies.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapptmdb.R
import com.example.movieapptmdb.feature.movies.presentation.MovieListEvents
import com.example.movieapptmdb.feature.movies.presentation.popular.PopularMoviesScreen
import com.example.movieapptmdb.feature.movies.presentation.upcoming.UpcomingMoviesScreen
import com.example.movieapptmdb.feature.movies.presentation.viewmodels.MovieListViewModel
import com.example.movieapptmdb.utils.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {

    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieState = movieListViewModel.movieListState.collectAsState().value
    val bottomNavController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                bottomNavController = bottomNavController,
                onEvent = movieListViewModel::onEvent
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (movieState.isCurrentPopularScreen) stringResource(R.string.popular_movies) else stringResource(
                            R.string.upcoming_movies
                        ),
                        fontSize = 28.sp
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface
                )
            )

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            NavHost(
                navController = bottomNavController,
                startDestination = Screens.PopularMovieListScreen.route
            ) {
                composable(Screens.PopularMovieListScreen.route) {
                    PopularMoviesScreen(
                        navController = navController,
                        movieListState = movieState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
                composable(Screens.UpcomingMovieListScreen.route) {
                    UpcomingMoviesScreen(
                        navController = navController,
                        movieListState = movieState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
                composable(Screens.UpcomingMovieListScreen.route) {
                    PopularMoviesScreen(
                        navController = navController,
                        movieListState = movieState,
                        onEvent = movieListViewModel::onEvent
                    )
                }
            }

        }

    }
}

@Composable
fun BottomNavigationBar(
    bottomNavController: NavHostController,
    onEvent: (MovieListEvents) -> Unit,
) {

    val items = listOf(
        BottomItem(
            title = stringResource(R.string.popular),
            icon = Icons.Rounded.Favorite
        ),
        BottomItem(
            title = stringResource(R.string.upcoming),
            icon = Icons.Rounded.Star
        ),

        )
    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)) {

            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(
                    selected = selected.intValue == index,
                    onClick = {
                        selected.intValue = index
                        when (selected.intValue) {
                            0 -> {
                                onEvent(MovieListEvents.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screens.PopularMovieListScreen.route)
                            }

                            1 -> {
                                onEvent(MovieListEvents.Navigate)
                                bottomNavController.popBackStack()
                                bottomNavController.navigate(Screens.UpcomingMovieListScreen.route)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = bottomItem.icon,
                            contentDescription = bottomItem.title,
                            tint = MaterialTheme.colorScheme.background
                        )
                    },
                    label = {
                        Text(
                            text = bottomItem.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }
}

data class BottomItem(
    val title: String,
    val icon: ImageVector,
)