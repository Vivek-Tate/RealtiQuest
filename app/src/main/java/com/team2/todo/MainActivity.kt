package com.team2.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.team2.todo.ui.theme.TODOTheme
import com.team2.todo.utils.NavHostControllerProvider
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.NotificationUtil
import com.team2.todo.view_model.PropertyListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchAndUpdateList();
        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // initalize utils
                    val navController = rememberNavController()
                    NavigationUtil.init(navController)
                    NotificationUtil.init(this)

                    //Navigation Provider i,e the Navigation graph
                    NavHostControllerProvider()

                }
            }
        }
    }

    private fun fetchAndUpdateList() {
        val viewModel: PropertyListViewModel by viewModels()
        // fetches the list, and update the list
//        viewModel.updatedUncompletedPropertyList()
//        viewModel.updatedCompletedPropertyList()

    }

}
