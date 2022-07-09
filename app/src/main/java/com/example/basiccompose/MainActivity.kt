package com.example.basiccompose

import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccompose.ui.theme.BasiccomposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasiccomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()

                }
            }
        }
    }
}
@Composable
fun MyApp(){
    var showonboard by remember {
        mutableStateOf(true)
    }
    if(showonboard)
    {
        Onboarding(onContinueClicked = {showonboard=false
        })
    }
        else
    {Greetings()}
}


@Composable
fun Greetings(names: List<String> = List(100){"$it"}) {
   LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names){names->
            Greeting(name = names)
        }
    }

}


@Composable
    fun Onboarding(onContinueClicked :()->Unit ){
       Box(
           contentAlignment = Alignment.Center,
           modifier = Modifier
               .fillMaxSize()
           ) {
        OutlinedButton(onClick =onContinueClicked ) {
            Text(
                text = stringResource(R.string.Continue),
                color = MaterialTheme.colors.primary

                )
        }
    }

}



@Composable
private fun Greeting(name: String) {
    var expends by remember { mutableStateOf(false) }
    val extrapad by animateDpAsState(
        if (expends) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier
            .padding(24.dp)
            .padding(bottom = extrapad)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expends =!expends}
            ) {
                Text(if(expends)"show less" else "show more")
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasiccomposeTheme {


        MyApp()
    }
}