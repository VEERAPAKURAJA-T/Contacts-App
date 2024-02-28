plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.veera"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.veera"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        dataBinding=true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    /*
     I need the annotation processor because
all operations like insert, delete, update and et-cetera are annotated also there.
As mentioned before, the Add entity Dao database annotations are included, so I need to use the annotation
processor.And the annotation processor is a tool that processes annotations at compile time to generate code and
perform other tasks.It's often used to automate repetitive code generation and to enforce specific behaviors in your code
base.So these annotations provide additional information to the compiler or tools about how to handle the
marked elements.For example, in Android you might use annotations like Override, suppress length or custom annotations
that you define.
     */
    val lifecycle_version = "2.6.2"

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
}