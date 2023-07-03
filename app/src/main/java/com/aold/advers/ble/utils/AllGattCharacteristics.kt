package com.aold.advers.ble.utils

import java.util.HashMap;
import java.util.UUID;


object AllGattCharacteristics {
    private val attributes: HashMap<String?, String?> = HashMap<String?, String?>()

    init {
        attributes["00002a00-0000-1000-8000-00805f9b34fb"] = "Device Name"
        attributes["00002a01-0000-1000-8000-00805f9b34fb"] = "Appearance"
        attributes["00002a02-0000-1000-8000-00805f9b34fb"] = "Peripheral Privacy Flag"
        attributes["00002a03-0000-1000-8000-00805f9b34fb"] = "Reconnection Address"
        attributes["00002a04-0000-1000-8000-00805f9b34fb"] =
            "Peripheral Preferred Connection Parameters"
        attributes["00002a05-0000-1000-8000-00805f9b34fb"] = "Service Changed"
        attributes["00002a06-0000-1000-8000-00805f9b34fb"] = "Alert Level"
        attributes["00002a07-0000-1000-8000-00805f9b34fb"] = "Tx Power Level"
        attributes["00002a08-0000-1000-8000-00805f9b34fb"] = "Date Time"
        attributes["00002a09-0000-1000-8000-00805f9b34fb"] = "Day of Week"
        attributes["00002a0a-0000-1000-8000-00805f9b34fb"] = "Day Date Time"
        attributes["00002a0b-0000-1000-8000-00805f9b34fb"] = "Exact Time 100"
        attributes["00002a0c-0000-1000-8000-00805f9b34fb"] = "Exact Time 256"
        attributes["00002a0d-0000-1000-8000-00805f9b34fb"] = "DST Offset"
        attributes["00002a0e-0000-1000-8000-00805f9b34fb"] = "Time Zone"
        attributes["00002a0f-0000-1000-8000-00805f9b34fb"] = "Local Time Information"
        attributes["00002a10-0000-1000-8000-00805f9b34fb"] = "Secondary Time Zone"
        attributes["00002a11-0000-1000-8000-00805f9b34fb"] = "Time with DST"
        attributes["00002a12-0000-1000-8000-00805f9b34fb"] = "Time Accuracy"
        attributes["00002a13-0000-1000-8000-00805f9b34fb"] = "Time Source"
        attributes["00002a14-0000-1000-8000-00805f9b34fb"] = "Reference Time Information"
        attributes["00002a15-0000-1000-8000-00805f9b34fb"] = "Time Broadcast"
        attributes["00002a16-0000-1000-8000-00805f9b34fb"] = "Time Update Control Point"
        attributes["00002a17-0000-1000-8000-00805f9b34fb"] = "Time Update State"
        attributes["00002a18-0000-1000-8000-00805f9b34fb"] = "Glucose Measurement"
        attributes["00002a19-0000-1000-8000-00805f9b34fb"] = "Battery Level"
        attributes["00002a1a-0000-1000-8000-00805f9b34fb"] = "Battery Power State"
        attributes["00002a1b-0000-1000-8000-00805f9b34fb"] = "Battery Level State"
        attributes["00002a1c-0000-1000-8000-00805f9b34fb"] = "Temperature Measurement"
        attributes["00002a1d-0000-1000-8000-00805f9b34fb"] = "Temperature Type"
        attributes["00002a1e-0000-1000-8000-00805f9b34fb"] = "Intermediate Temperature"
        attributes["00002a1f-0000-1000-8000-00805f9b34fb"] = "Temperature Celsius"
        attributes["00002a20-0000-1000-8000-00805f9b34fb"] = "Temperature Fahrenheit"
        attributes["00002a21-0000-1000-8000-00805f9b34fb"] = "Measurement Interval"
        attributes["00002a22-0000-1000-8000-00805f9b34fb"] = "Boot Keyboard Input Report"
        attributes["00002a23-0000-1000-8000-00805f9b34fb"] = "System ID"
        attributes["00002a24-0000-1000-8000-00805f9b34fb"] = "Model Number String"
        attributes["00002a25-0000-1000-8000-00805f9b34fb"] = "Serial Number String"
        attributes["00002a26-0000-1000-8000-00805f9b34fb"] = "Firmware Revision String"
        attributes["00002a27-0000-1000-8000-00805f9b34fb"] = "Hardware Revision String"
        attributes["00002a28-0000-1000-8000-00805f9b34fb"] = "Software Revision String"
        attributes["00002a29-0000-1000-8000-00805f9b34fb"] = "Manufacturer Name String"
        attributes["00002a2a-0000-1000-8000-00805f9b34fb"] =
            "IEEE 11073-20601 Regulatory Certification Data List"
        attributes["00002a2b-0000-1000-8000-00805f9b34fb"] = "Current Time"
        attributes["00002a2c-0000-1000-8000-00805f9b34fb"] = "Magnetic Declination"
        attributes["00002a2f-0000-1000-8000-00805f9b34fb"] = "Position 2D"
        attributes["00002a30-0000-1000-8000-00805f9b34fb"] = "Position 3D"
        attributes["00002a31-0000-1000-8000-00805f9b34fb"] = "Scan Refresh"
        attributes["00002a32-0000-1000-8000-00805f9b34fb"] = "Boot Keyboard Output Report"
        attributes["00002a33-0000-1000-8000-00805f9b34fb"] = "Boot Mouse Input Report"
        attributes["00002a34-0000-1000-8000-00805f9b34fb"] = "Glucose Measurement Context"
        attributes["00002a35-0000-1000-8000-00805f9b34fb"] = "Blood Pressure Measurement"
        attributes["00002a36-0000-1000-8000-00805f9b34fb"] = "Intermediate Cuff Pressure"
        attributes["00002a37-0000-1000-8000-00805f9b34fb"] = "Heart Rate Measurement"
        attributes["00002a38-0000-1000-8000-00805f9b34fb"] = "Body Sensor Location"
        attributes["00002a39-0000-1000-8000-00805f9b34fb"] = "Heart Rate Control Point"
        attributes["00002a3a-0000-1000-8000-00805f9b34fb"] = "Removable"
        attributes["00002a3b-0000-1000-8000-00805f9b34fb"] = "Service Required"
        attributes["00002a3c-0000-1000-8000-00805f9b34fb"] = "Scientific Temperature Celsius"
        attributes["00002a3d-0000-1000-8000-00805f9b34fb"] = "String"
        attributes["00002a3e-0000-1000-8000-00805f9b34fb"] = "Network Availability"
        attributes["00002a3f-0000-1000-8000-00805f9b34fb"] = "Alert Status"
        attributes["00002a40-0000-1000-8000-00805f9b34fb"] = "Ringer Control point"
        attributes["00002a41-0000-1000-8000-00805f9b34fb"] = "Ringer Setting"
        attributes["00002a42-0000-1000-8000-00805f9b34fb"] = "Alert Category ID Bit Mask"
        attributes["00002a43-0000-1000-8000-00805f9b34fb"] = "Alert Category ID"
        attributes["00002a44-0000-1000-8000-00805f9b34fb"] = "Alert Notification Control Point"
        attributes["00002a45-0000-1000-8000-00805f9b34fb"] = "Unread Alert Status"
        attributes["00002a46-0000-1000-8000-00805f9b34fb"] = "New Alert"
        attributes["00002a47-0000-1000-8000-00805f9b34fb"] = "Supported New Alert Category"
        attributes["00002a48-0000-1000-8000-00805f9b34fb"] = "Supported Unread Alert Category"
        attributes["00002a49-0000-1000-8000-00805f9b34fb"] = "Blood Pressure Feature"
        attributes["00002a4a-0000-1000-8000-00805f9b34fb"] = "HID Information"
        attributes["00002a4b-0000-1000-8000-00805f9b34fb"] = "Report Map"
        attributes["00002a4c-0000-1000-8000-00805f9b34fb"] = "HID Control Point"
        attributes["00002a4d-0000-1000-8000-00805f9b34fb"] = "Report"
        attributes["00002a4e-0000-1000-8000-00805f9b34fb"] = "Protocol Mode"
        attributes["00002a4f-0000-1000-8000-00805f9b34fb"] = "Scan Interval Window"
        attributes["00002a50-0000-1000-8000-00805f9b34fb"] = "PnP ID"
        attributes["00002a51-0000-1000-8000-00805f9b34fb"] = "Glucose Feature"
        attributes["00002a52-0000-1000-8000-00805f9b34fb"] = "Record Access Control Point"
        attributes["00002a53-0000-1000-8000-00805f9b34fb"] = "RSC Measurement"
        attributes["00002a54-0000-1000-8000-00805f9b34fb"] = "RSC Feature"
        attributes["00002a55-0000-1000-8000-00805f9b34fb"] = "SC Control Point"
        attributes["00002a56-0000-1000-8000-00805f9b34fb"] = "Digital"
        attributes["00002a57-0000-1000-8000-00805f9b34fb"] = "Digital Output"
        attributes["00002a58-0000-1000-8000-00805f9b34fb"] = "Analog"
        attributes["00002a59-0000-1000-8000-00805f9b34fb"] = "Analog Output"
        attributes["00002a5a-0000-1000-8000-00805f9b34fb"] = "Aggregate"
        attributes["00002a5b-0000-1000-8000-00805f9b34fb"] = "CSC Measurement"
        attributes["00002a5c-0000-1000-8000-00805f9b34fb"] = "CSC Feature"
        attributes["00002a5d-0000-1000-8000-00805f9b34fb"] = "Sensor Location"
        attributes["00002a5e-0000-1000-8000-00805f9b34fb"] = "PLX Spot-Check Measurement"
        attributes["00002a5f-0000-1000-8000-00805f9b34fb"] =
            "PLX Continuous Measurement Characteristic"
        attributes["00002a60-0000-1000-8000-00805f9b34fb"] = "PLX Features"
        attributes["00002a62-0000-1000-8000-00805f9b34fb"] = "Pulse Oximetry Control Point"
        attributes["00002a63-0000-1000-8000-00805f9b34fb"] = "Cycling Power Measurement"
        attributes["00002a64-0000-1000-8000-00805f9b34fb"] = "Cycling Power Vector"
        attributes["00002a65-0000-1000-8000-00805f9b34fb"] = "Cycling Power Feature"
        attributes["00002a66-0000-1000-8000-00805f9b34fb"] = "Cycling Power Control Point"
        attributes["00002a67-0000-1000-8000-00805f9b34fb"] = "Location and Speed Characteristic"
        attributes["00002a68-0000-1000-8000-00805f9b34fb"] = "Navigation"
        attributes["00002a69-0000-1000-8000-00805f9b34fb"] = "Position Quality"
        attributes["00002a6a-0000-1000-8000-00805f9b34fb"] = "LN Feature"
        attributes["00002a6b-0000-1000-8000-00805f9b34fb"] = "LN Control Point"
        attributes["00002a6c-0000-1000-8000-00805f9b34fb"] = "Elevation"
        attributes["00002a6d-0000-1000-8000-00805f9b34fb"] = "Pressure"
        attributes["00002a6e-0000-1000-8000-00805f9b34fb"] = "Temperature"
        attributes["00002a6f-0000-1000-8000-00805f9b34fb"] = "Humidity"
        attributes["00002a70-0000-1000-8000-00805f9b34fb"] = "True Wind Speed"
        attributes["00002a71-0000-1000-8000-00805f9b34fb"] = "True Wind Direction"
        attributes["00002a72-0000-1000-8000-00805f9b34fb"] = "Apparent Wind Speed"
        attributes["00002a73-0000-1000-8000-00805f9b34fb"] = "Apparent Wind Direction"
        attributes["00002a74-0000-1000-8000-00805f9b34fb"] = "Gust Factor"
        attributes["00002a75-0000-1000-8000-00805f9b34fb"] = "Pollen Concentration"
        attributes["00002a76-0000-1000-8000-00805f9b34fb"] = "UV Index"
        attributes["00002a77-0000-1000-8000-00805f9b34fb"] = "Irradiance"
        attributes["00002a78-0000-1000-8000-00805f9b34fb"] = "Rainfall"
        attributes["00002a79-0000-1000-8000-00805f9b34fb"] = "Wind Chill"
        attributes["00002a7a-0000-1000-8000-00805f9b34fb"] = "Heat Index"
        attributes["00002a7b-0000-1000-8000-00805f9b34fb"] = "Dew Point"
        attributes["00002a7d-0000-1000-8000-00805f9b34fb"] = "Descriptor Value Changed"
        attributes["00002a7e-0000-1000-8000-00805f9b34fb"] = "Aerobic Heart Rate Lower Limit"
        attributes["00002a7f-0000-1000-8000-00805f9b34fb"] = "Aerobic Threshold"
        attributes["00002a80-0000-1000-8000-00805f9b34fb"] = "Age"
        attributes["00002a81-0000-1000-8000-00805f9b34fb"] = "Anaerobic Heart Rate Lower Limit"
        attributes["00002a82-0000-1000-8000-00805f9b34fb"] = "Anaerobic Heart Rate Upper Limit"
        attributes["00002a83-0000-1000-8000-00805f9b34fb"] = "Anaerobic Threshold"
        attributes["00002a84-0000-1000-8000-00805f9b34fb"] = "Aerobic Heart Rate Upper Limit"
        attributes["00002a85-0000-1000-8000-00805f9b34fb"] = "Date of Birth"
        attributes["00002a86-0000-1000-8000-00805f9b34fb"] = "Date of Threshold Assessment"
        attributes["00002a87-0000-1000-8000-00805f9b34fb"] = "Email Address"
        attributes["00002a88-0000-1000-8000-00805f9b34fb"] = "Fat Burn Heart Rate Lower Limit"
        attributes["00002a89-0000-1000-8000-00805f9b34fb"] = "Fat Burn Heart Rate Upper Limit"
        attributes["00002a8a-0000-1000-8000-00805f9b34fb"] = "First Name"
        attributes["00002a8b-0000-1000-8000-00805f9b34fb"] = "Five Zone Heart Rate Limits"
        attributes["00002a8c-0000-1000-8000-00805f9b34fb"] = "Gender"
        attributes["00002a8d-0000-1000-8000-00805f9b34fb"] = "Heart Rate Max"
        attributes["00002a8e-0000-1000-8000-00805f9b34fb"] = "Height"
        attributes["00002a8f-0000-1000-8000-00805f9b34fb"] = "Hip Circumference"
        attributes["00002a90-0000-1000-8000-00805f9b34fb"] = "Last Name"
        attributes["00002a91-0000-1000-8000-00805f9b34fb"] = "Maximum Recommended Heart Rate"
        attributes["00002a92-0000-1000-8000-00805f9b34fb"] = "Resting Heart Rate"
        attributes["00002a93-0000-1000-8000-00805f9b34fb"] =
            "Sport Type for Aerobic and Anaerobic Thresholds"
        attributes["00002a94-0000-1000-8000-00805f9b34fb"] = "Three Zone Heart Rate Limits"
        attributes["00002a95-0000-1000-8000-00805f9b34fb"] = "Two Zone Heart Rate Limit"
        attributes["00002a96-0000-1000-8000-00805f9b34fb"] = "VO2 Max"
        attributes["00002a97-0000-1000-8000-00805f9b34fb"] = "Waist Circumference"
        attributes["00002a98-0000-1000-8000-00805f9b34fb"] = "Weight"
        attributes["00002a99-0000-1000-8000-00805f9b34fb"] = "Database Change Increment"
        attributes["00002a9a-0000-1000-8000-00805f9b34fb"] = "User Index"
        attributes["00002a9b-0000-1000-8000-00805f9b34fb"] = "Body Composition Feature"
        attributes["00002a9c-0000-1000-8000-00805f9b34fb"] = "Body Composition Measurement"
        attributes["00002a9d-0000-1000-8000-00805f9b34fb"] = "Weight Measurement"
        attributes["00002a9e-0000-1000-8000-00805f9b34fb"] = "Weight Scale Feature"
        attributes["00002a9f-0000-1000-8000-00805f9b34fb"] = "User Control Point"
        attributes["00002aa0-0000-1000-8000-00805f9b34fb"] = "Magnetic Flux Density - 2D"
        attributes["00002aa1-0000-1000-8000-00805f9b34fb"] = "Magnetic Flux Density - 3D"
        attributes["00002aa2-0000-1000-8000-00805f9b34fb"] = "Language"
        attributes["00002aa3-0000-1000-8000-00805f9b34fb"] = "Barometric Pressure Trend"
        attributes["00002aa4-0000-1000-8000-00805f9b34fb"] = "Bond Management Control Point"
        attributes["00002aa5-0000-1000-8000-00805f9b34fb"] = "Bond Management Features"
        attributes["00002aa6-0000-1000-8000-00805f9b34fb"] = "Central Address Resolution"
        attributes["00002aa7-0000-1000-8000-00805f9b34fb"] = "CGM Measurement"
        attributes["00002aa8-0000-1000-8000-00805f9b34fb"] = "CGM Feature"
        attributes["00002aa9-0000-1000-8000-00805f9b34fb"] = "CGM Status"
        attributes["00002aaa-0000-1000-8000-00805f9b34fb"] = "CGM Session Start Time"
        attributes["00002aab-0000-1000-8000-00805f9b34fb"] = "CGM Session Run Time"
        attributes["00002aac-0000-1000-8000-00805f9b34fb"] = "CGM Specific Ops Control Point"
        attributes["00002aad-0000-1000-8000-00805f9b34fb"] = "Indoor Positioning Configuration"
        attributes["00002aae-0000-1000-8000-00805f9b34fb"] = "Latitude"
        attributes["00002aaf-0000-1000-8000-00805f9b34fb"] = "Longitude"
        attributes["00002ab0-0000-1000-8000-00805f9b34fb"] = "Local North Coordinate"
        attributes["00002ab1-0000-1000-8000-00805f9b34fb"] = "Local East Coordinate"
        attributes["00002ab2-0000-1000-8000-00805f9b34fb"] = "Floor Number"
        attributes["00002ab3-0000-1000-8000-00805f9b34fb"] = "Altitude"
        attributes["00002ab4-0000-1000-8000-00805f9b34fb"] = "Uncertainty"
        attributes["00002ab5-0000-1000-8000-00805f9b34fb"] = "Location Name"
        attributes["00002ab6-0000-1000-8000-00805f9b34fb"] = "URI"
        attributes["00002ab7-0000-1000-8000-00805f9b34fb"] = "HTTP Headers"
        attributes["00002ab8-0000-1000-8000-00805f9b34fb"] = "HTTP Status Code"
        attributes["00002ab9-0000-1000-8000-00805f9b34fb"] = "HTTP Entity Body"
        attributes["00002aba-0000-1000-8000-00805f9b34fb"] = "HTTP Control Point"
        attributes["00002abb-0000-1000-8000-00805f9b34fb"] = "HTTPS Security"
        attributes["00002abc-0000-1000-8000-00805f9b34fb"] = "TDS Control Point"
        attributes["00002abd-0000-1000-8000-00805f9b34fb"] = "OTS Feature"
        attributes["00002abe-0000-1000-8000-00805f9b34fb"] = "Object Name"
        attributes["00002abf-0000-1000-8000-00805f9b34fb"] = "Object Type"
        attributes["00002ac0-0000-1000-8000-00805f9b34fb"] = "Object Size"
        attributes["00002ac1-0000-1000-8000-00805f9b34fb"] = "Object First-Created"
        attributes["00002ac2-0000-1000-8000-00805f9b34fb"] = "Object Last-Modified"
        attributes["00002ac3-0000-1000-8000-00805f9b34fb"] = "Object ID"
        attributes["00002ac4-0000-1000-8000-00805f9b34fb"] = "Object Properties"
        attributes["00002ac5-0000-1000-8000-00805f9b34fb"] = "Object Action Control Point"
        attributes["00002ac6-0000-1000-8000-00805f9b34fb"] = "Object List Control Point"
        attributes["00002ac7-0000-1000-8000-00805f9b34fb"] = "Object List Filter"
        attributes["00002ac8-0000-1000-8000-00805f9b34fb"] = "Object Changed"
        attributes["00002ac9-0000-1000-8000-00805f9b34fb"] = "Resolvable Private Address Only"
        attributes["00002acc-0000-1000-8000-00805f9b34fb"] = "Fitness Machine Feature"
        attributes["00002acd-0000-1000-8000-00805f9b34fb"] = "Treadmill Data"
        attributes["00002ace-0000-1000-8000-00805f9b34fb"] = "Cross Trainer Data"
        attributes["00002acf-0000-1000-8000-00805f9b34fb"] = "Step Climber Data"
        attributes["00002ad0-0000-1000-8000-00805f9b34fb"] = "Stair Climber Data"
        attributes["00002ad1-0000-1000-8000-00805f9b34fb"] = "Rower Data"
        attributes["00002ad2-0000-1000-8000-00805f9b34fb"] = "Indoor Bike Data"
        attributes["00002ad3-0000-1000-8000-00805f9b34fb"] = "Training Status"
        attributes["00002ad4-0000-1000-8000-00805f9b34fb"] = "Supported Speed Range"
        attributes["00002ad5-0000-1000-8000-00805f9b34fb"] = "Supported Inclination Range"
        attributes["00002ad6-0000-1000-8000-00805f9b34fb"] = "Supported Resistance Level Range"
        attributes["00002ad7-0000-1000-8000-00805f9b34fb"] = "Supported Heart Rate Range"
        attributes["00002ad8-0000-1000-8000-00805f9b34fb"] = "Supported Power Range"
        attributes["00002ad9-0000-1000-8000-00805f9b34fb"] = "Fitness Machine Control Point"
        attributes["00002ada-0000-1000-8000-00805f9b34fb"] = "Fitness Machine Status"
        attributes["00002aed-0000-1000-8000-00805f9b34fb"] = "Date UTC"
        attributes["00002b1d-0000-1000-8000-00805f9b34fb"] = "RC Feature"
        attributes["00002b1e-0000-1000-8000-00805f9b34fb"] = "RC Settings"
        attributes["00002b1f-0000-1000-8000-00805f9b34fb"] =
            "Reconnection Configuration Control Point"
    }

    fun lookup(uuid: UUID): String {
        return lookup(uuid.toString(), "--")
    }

    @JvmOverloads
    fun lookup(uuid: String?, defaultName: String = "--"): String {
        val name = attributes[uuid]
        return name ?: defaultName
    }
}