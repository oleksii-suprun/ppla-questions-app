import com.github.gradle.node.npm.task.NpxTask

plugins {
    id("com.github.node-gradle.node") version "7.1.0"
}

tasks {

    val npmRunBuild by registering(NpxTask::class) {
        dependsOn(npmInstall)
        command = "npm"
        args = listOf("run","build")
    }

    val npmTest by registering(NpxTask::class) {
        dependsOn(npmInstall)
        command = "npm"
        args = listOf("test")
    }
}
