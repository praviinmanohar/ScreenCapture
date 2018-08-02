# ScreenCapture
Take a Full Screenshot of ScrollView or Nested ScrollView in Android

Usage :
1. copy the ScreenCapture file into your Project
2. Declare 
  " private ScreenCapture screenCapture; "
3. Initialise
  " screenCapture = new ScreenCapture(this, scrollView, R.layout.activity_main); "
4. call thid method to take the ful screenshot
  " try {
                capture.onScrollCaptureImg();
            } catch (IOException e) {
                e.printStackTrace();
            } "
            
            ---- THANK YOU ----
