package com.redhat.developer.demo;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeWorkerResourceIT extends WorkerResourceTest {

    // Execute the same tests but in native mode.
}
