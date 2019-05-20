# Android-Java-Retrofit-MVVM

After the first round of interviews I was asked to build this small Java project.


![](image/app.gif)


Android Coding Challenge:

Create an Android application with 3 buttons along the top of the screen that can do the 3 parts outlined below:

 

(Button 1) 

Make an HTTP request and display the JSON returned on the screen in an organized format. The choice of layout of the data is up to you.   Please use any data you would like from any publicly open API such as the https://www.data.gov/ website below

https://www.data.gov/developers/apis to make your request and pull the JSON. We recommend checking the website for a list of possible requests. 

For example, feel free to pull data from the Regulations API (you will need to request an API key): https://regulationsgov.github.io/developers/basics/

 

(Button 2)

Display the current GPS coordinates of the device, as well as the calculated distance between the current location and San Francisco, CA. This can either be the distance to the center of the city, or to its border, just please specify which you choose. Note: Remember to consider permissions for devices running API 23 and above. 

 

(Button 3)

Collect a list of installed applications on the device, and determine their respective code sizes, data sizes, and cache sizes on the device. Store this information in a String delimited by commas, in this format: "Name, packageName, codeSize, dataSize, cacheSize, Name, packageName, ..." i.e. "Google Play Store, com.android.vending, 9908224, 61440, 94208"

 

BONUS (replaces above Button 3 NDK portion):

NDK portion: send the String of installed applications to a C/C++ function that utilizes a Crypto library and encrypt the String using AES symmetric cryptography. Then return the encrypted bytes to the Java layer and decrypt the data. Finally, print the list of installed applications along with their sizes to the screen from the decrypted byte array. 

You may utilize any Cryptographic libraries you wish to use in both the Java and NDK code.

![Question](image/1.PNG?raw=true)
