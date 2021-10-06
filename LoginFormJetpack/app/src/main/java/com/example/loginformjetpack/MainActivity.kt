package com.example.loginformjetpack

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loginformjetpack.ui.theme.LoginFormJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { TopAppBar(
                    title = { Text(text = "Login Form")},
                    elevation = 2.dp
                )}
            ) {
                LoginBody(this)
            }
//            LoginFormJetpackTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//
//                }
//            }
        }
    }
}

@Composable
fun LoginBody(context: Context) {
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var maxNumber = 10
    Box{
       Image(
           painter = painterResource(id = R.drawable.background_image),
           contentDescription = "background image",
           Modifier.fillMaxSize(),
           alpha = 0.7F,
           contentScale = ContentScale.FillBounds
       )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter the Email-Id") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.padding(5.dp),
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { if(it.length <= maxNumber) phoneNumber = it },
                label = { Text("Enter the Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.padding(5.dp),
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it},
                label = { Text("Enter the First Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(5.dp),
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Enter the Last Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(5.dp),
            )
            Button(
                onClick = {
                    if(email.equals("") || phoneNumber.equals("") || firstName.equals("") || lastName.equals("")){
                        toast("Please Enter all Fields",context)
                    }else {
                        validate(email, phoneNumber, firstName, lastName, context)
                    }
                },
                modifier = Modifier.padding(5.dp),
                enabled = true
            ) {
                Text("Sign In")
            }
        }
    }
}

fun validate(email:String,phoneNumber:String,fname:String,lname:String,context:Context){
    val namePattern = Regex ("[a-zA-Z.\\s]+")
    val number = Regex("^[0-9]*\$")
    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        toast("Enter Valid Mail-Id",context)
    }else if(!phoneNumber.matches(number) || phoneNumber.length < 10){
        toast("Invalid PhoneNumber",context)
    }else if(!fname.matches(namePattern)){
        toast("Enter Valid First Name",context)
    }else if(!lname.matches(namePattern)){
        toast("Enter Valid Last Name",context)
    }else{
        toast("Sign In Successful...",context)
    }
}

fun toast(text:String,context: Context){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show();
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginFormJetpackTheme {
//        Greeting("Android")
    }
}