# Image Downloader

## Descripción

Este programa en Java descarga todas las imágenes con extensiones `.jpeg`, `.jpg` y `.png` enlazadas desde una página web y las guarda en un directorio del sistema operativo especificado por el usuario. La descarga se realiza de forma concurrente utilizando varios hilos (`threads`), lo que mejora el rendimiento para páginas con múltiples imágenes.

## Diseño

- **Componentes principales**:
  - **`ImageDownloadService`**: Servicio que coordina la descarga de las imágenes.
  - **`ImageDownloadTask`**: Tarea que descarga una imagen específica de forma concurrente.
  
- **Tecnologías utilizadas**:
  - **Spring Boot**: Organiza el proyecto y simplifica la ejecución de la aplicación.
  - **JSoup**: Extrae las URLs de las imágenes de la página web.
  - **Java NIO**: Descarga las imágenes en el sistema de archivos.
  
- **Concurrencia**: Se utiliza `ExecutorService` con un pool de hilos para descargar múltiples imágenes en paralelo, mejorando el rendimiento en sitios con muchas imágenes.

- **Pruebas unitarias**: Se han implementado pruebas para verificar la funcionalidad del servicio de descarga de imágenes, asegurando que se manejen correctamente las URLs válidas e inválidas y que las imágenes se descarguen en el directorio especificado.

## Uso
 **Ejecución**:
    ```bash
    java -jar image-downloader.jar "https://ejemplo.com" "/ruta/donde/descargar"
    ```

## Consideraciones
- **Errores**: Si la descarga de una imagen falla, se registra el error pero el proceso continúa con las demás imágenes.
- **Concurrencia**: Se utiliza un pool de 10 hilos para la descarga concurrente.
- **Descarga**: Si la ruta de descarga especificada no existe en el sistema, el programa creará automáticamente la estructura de directorios necesaria para garantizar que las imágenes se guarden correctamente.

## Mejoras futuras
- Manejo de nombres duplicados o imágenes ya existentes.
- Soporte para más tipos de imágenes (ej. GIF, BMP).
- Posibilidad de que el usuario introduzca el número de hilos como parámetro al ejecutar la aplicación, con un valor por defecto de 10 hilos si no se especifica.