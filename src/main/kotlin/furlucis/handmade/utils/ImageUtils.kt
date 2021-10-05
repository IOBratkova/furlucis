package furlucis.handmade.utils

import org.springframework.web.multipart.MultipartFile
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime


private val extensions: Array<String> = arrayOf("jpg", "png", "jpeg")

fun getFilePath(imagePath: String) : String {
    val date = LocalDateTime.now()
    return imagePath + "/" + date.dayOfMonth + date.month + date.year
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

fun writeByteFile(path: Path, file: MultipartFile) {
    if (!Files.exists(path)) {
        throw NullPointerException() //TODO ex
    }
    val stream = BufferedOutputStream(FileOutputStream(path.toFile()))
    val bytes = file.bytes
    stream.write(bytes)
    stream.close()
}

fun writeFile(filePath: String, file: MultipartFile, extension: String) : Path {
    val path = Files.createFile(Paths.get(filePath + "/" + file.hashCode() + "." + extension))
    val newPath = Paths.get(filePath)
    if (!Files.exists(newPath)) {
        Files.createDirectories(newPath)
    }
    writeByteFile(path, file)
    return path
}


fun loadFile(file: MultipartFile, imagesPath: String, save: Boolean) : String {
    if (file.isEmpty && file.originalFilename == null) {
        throw NullPointerException() //TODO ex
    }
    val extension = getImageFileExtension(file.originalFilename!!)
    val filePath = getFilePath(imagesPath)
    if (save) {
        return writeFile(filePath, file, extension).toUri().toString()
    }
    return "/default"
}
