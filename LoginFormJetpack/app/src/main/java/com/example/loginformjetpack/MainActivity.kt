package com.example.loginformjetpack

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loginformjetpack.ui.theme.LoginFormJetpackTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContent {
            Scaffold(
                topBar = { TopAppBar(
                    title = { Text(text = "Login Form")},
                    elevation = 2.dp
                )}
            ) {
                LoginBody()
            }
        }
    }

    @Composable
    fun LoginBody() {
        var email by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        val maxNumber = 10
        val focusManager = LocalFocusManager.current
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
                    label = { Text("Email Id") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,imeAction = ImeAction.Next),
                    modifier = Modifier.padding(5.dp),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { if(it.length <= maxNumber) phoneNumber = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
                    modifier = Modifier.padding(5.dp),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it},
                    label = { Text("First Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Next),
                    modifier = Modifier.padding(5.dp),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done),
                    modifier = Modifier.padding(5.dp),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)})
                )
                Button(
                    onClick = {
                        if(email.equals("") || phoneNumber.equals("") || firstName.equals("") || lastName.equals("")){
                            toast("Please Enter all Fields")
                        }else {
                            var valid = validate(email, phoneNumber, firstName, lastName)
                            if(valid){
                                email = ""
                                phoneNumber = ""
                                firstName = ""
                                lastName = ""
                            }
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

    fun validate(email:String,phoneNumber:String,fname:String,lname:String) : Boolean{
        val namePattern = Regex ("[a-zA-Z.\\s]+")
        val number = Regex("^[0-9]*\$")
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            toast("Enter Valid Mail-Id")
        }else if(!phoneNumber.matches(number) || phoneNumber.length < 10){
            toast("Invalid PhoneNumber")
        }else if(!fname.matches(namePattern)){
            toast("Enter Valid First Name")
        }else if(!lname.matches(namePattern)){
            toast("Enter Valid Last Name")
        }else{
            toast("Sign In Successful...")
            return true
        }
        return false
    }

    fun toast(text:String){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginFormJetpackTheme {
//        Greeting("Android")
    }
}