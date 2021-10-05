package furlucis.handmade.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.xml.stream.Location

private val extensions: Array<String> = arrayOf("jpg", "png", "jpeg")

fun getFilePath(imagePath: String) : String {
    val date = Date()
    return imagePath + "/" + date.day + date.month + date.year
}

fun getImageFileExtension(filename: String) : String {
    val filenameArray = filename.split("\\pP".toRegex())
    val len = filenameArray.size - 1
    if (filenameArray[len] !in extensions) {
        throw NullPointerException() //TODO ex
    }
    return filenameArray[len]
}

fun createDir(path: String) {
    val newPath = Paths.get(path)
    if (!Files.exists(newPath)) {
        Files.createDirectories(newPath)
    }
}

fun loadFile(file: MultipartFile, location: String, save: Boolean) : String {
    if (file.isEmpty && file.originalFilename == null) {
        throw NullPointerException() //TODO ex
    }
    val extension = getImageFileExtension(file.originalFilename!!)
    val filePath = getFilePath(location)
    if (save) {
        createDir(filePath)
        val path = Files.createFile(Paths.get(filePath + "/" + file.hashCode() + "." + extension))
        writeFile(path, file)
        return path.toUri().toString()
    }
    return "/default"
}

fun prepareImageFile(file: MultipartFile, path: String) : Path {
    if (file.isEmpty && file.originalFilename == null) {
        throw NullPointerException() //TODO ex
    }
    val extension = getImageFileExtension(file.originalFilename!!)
    val filePath = getFilePath(path)
    createDir(filePath)
    return Files.createFile(Paths.get(filePath + "/" + file.hashCode() + "." + extension))
}

fun writeFile(path: Path, file: MultipartFile) {
    if (!Files.exists(path)) {
        throw NullPointerException() //TODO ex
    }
    val stream = BufferedOutputStream(FileOutputStream(path.toFile()))
    val bytes = file.bytes
    stream.write(bytes)
    stream.close()
}

