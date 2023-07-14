//package com.aold.advers.ble.presentation.timers
//
//import android.widget.DatePicker
//import android.widget.EditText
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import com.aold.advers.R
//
///**
// * @author Kirilin Yury on 14.07.2023.
// */
//@Composable
//fun TimerLayout() {
//
//    var chosenYear = 0
//    var chosenMonth = 0
//    var chosenDay = 0
//    var chosenHour = 0
//    var chosenMin = 0
//
//    binding.composeView.apply {
//
//
//    }
//
//
//
//    // 2 Access View Components using their Id
//    val descriptionText = findViewById<EditText>(R.id.editText)
//    val button = findViewById<Button>(R.id.setBtn)
//    val datePicker = findViewById<DatePicker>(R.id.datePicker)
//    val timePicker = findViewById<TimePicker>(R.id.timePicker)
//    val today = Calendar.getInstance()
//
//    DatePicker(
//
//    )
//
//    // 3 initialize of datePicker using the current day as starting parameters and then
//    // pass the userSelected to the variables created
//    datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
//        today.get(Calendar.DAY_OF_MONTH)
//    ) { _, year, month, day ->
//        chosenYear = year
//        chosenMonth = month
//        chosenDay = day
//    }
//    // 4 Add the Listener to gain access to user selection in the TimePicker and
//    // then assign the selected values to the variables created above
//    timePicker.setOnTimeChangedListener { _, hour, minute ->
//        chosenHour = hour
//        chosenMin  = minute
//    }
//
//    // 5 Add the Listener to listen to click events and execute the code to setNotification
//    button.setOnClickListener {
//
//        // 6 Get the DateTime the user selected
//        val userSelectedDateTime =Calendar.getInstance()
//        userSelectedDateTime.set(chosenYear, chosenMonth, chosenDay, chosenHour , chosenMin)
//
//        // 7 Next get DateTime for today
//        val todayDateTime = Calendar.getInstance()
//
//        // 8
//        val delayInSeconds = (userSelectedDateTime.timeInMillis/1000L) - (todayDateTime.timeInMillis/1000L)
//
//        // 9
//        createWorkRequest(descriptionText.text.toString(), delayInSeconds)
//
//        // 10
//        Toast.makeText(this, "Reminder set", Toast.LENGTH_SHORT).show()
//    }
//}
//
//// Private Function to create the OneTimeWorkRequest
//private fun createWorkRequest(message: String,timeDelayInSeconds: Long  ) {
//    val myWorkRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
//        .setInitialDelay(timeDelayInSeconds, TimeUnit.SECONDS)
//        .setInputData(workDataOf(
//            "title" to "Reminder",
//            "message" to message,
//        )
//        )
//        .build()
//
//    WorkManager.getInstance(this).enqueue(myWorkRequest)
//}
//
//}