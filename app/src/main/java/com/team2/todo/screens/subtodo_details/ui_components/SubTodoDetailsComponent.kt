import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.sharp.DateRange
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team2.todo.R
import com.team2.todo.common_ui_components.CommonAppBar
import com.team2.todo.common_ui_components.EmptyList
import com.team2.todo.data.datautils.LocalDatetimeToWords
import com.team2.todo.screens.subtodo_details.ui_components.DisplaySubTodoImage
import com.team2.todo.screens.subtodo_details.view_model.SubTodoDetailsViewModel
import com.team2.todo.ui.theme.GreyColor
import com.team2.todo.utils.AppUtil
import com.team2.todo.utils.NavigationUtil
import com.team2.todo.utils.Screen

@Composable
fun SubTodoDetailsComponent(viewModel: SubTodoDetailsViewModel, subTodoId: Long) {

    viewModel.getSubTodoById(subTodoId)
    val propertySubTaskState by remember { viewModel.subTodo }.collectAsState()
    if (propertySubTaskState == null) {
        EmptyList(title = "No Active Sales Found", drawableID = R.drawable.ic_no_in_sale_list)

    } else {


        Scaffold(topBar = {
            propertySubTaskState?.title?.let {
                CommonAppBar(
                    text = it,
                    actions = {
                        Icon(Icons.Filled.Edit,
                            "Extended floating action button.",
                            tint = GreyColor,
                            modifier = Modifier
                                .border(
                                    2.dp, GreyColor, shape = RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp)
                                .clickable {
                                    //SUB-TODO handle navigation to edit

                                })
                    },
                )
            }
        }) { padding ->


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .width(700.dp)
                        .fillMaxHeight(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {

                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            propertySubTaskState?.priority?.let {
                                Icon(
                                    imageVector = Icons.Sharp.Warning,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(18.dp),
                                    tint = AppUtil.getPriorityColor(it)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = AppUtil.getPriorityString(it),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                        Divider( modifier = Modifier.padding(top = 8.dp , bottom = 8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Sharp.DateRange,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(18.dp),
                                tint = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = LocalDatetimeToWords.formatLocalDateTimeAsWords(
                                    propertySubTaskState?.dueDate
                                ),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                textAlign = TextAlign.Start
                            )
                        }
                        Divider( modifier = Modifier.padding(top = 8.dp , bottom = 2.dp))
                        DisplaySubTodoImage(propertySubTaskState?.image)
                        Divider( modifier = Modifier.padding(top = 2.dp , bottom = 8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            ) {

                            propertySubTaskState?.description?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge,
                                    maxLines = 10,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}

