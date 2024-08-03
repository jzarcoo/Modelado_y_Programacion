package mx.unam.ciencias.modelado.proyecto2.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * <p> Clase que se encarga de recibir e interpretar el clima actual a partir de 
 * una peticion a la API de la CONAGUA.
 * </p>
 */
public class CargadorClima {

    // Constructor privado para evitar instancias de la clase
    private CargadorClima() {}

    /**
     * Metodo estatico que se encarga de cargar el clima actual
     * a partir de una peticion a la API de la CONAGUA. Permite al controlador tener los climas
     * actualizados.
     */
    public static void cargaClima() {
        try {
            // URL de la api
            URL url = new URL("https://smn.conagua.gob.mx/tools/GUI/webservices/?method=1");

            // Abrir conexion HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Verificar si la respuesta es exitosa
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Error al obtener la respuesta: " + responseCode);
            }

            // Crear un flujo de entrada gzip para descomprimir el archivo
            InputStream inputStream = connection.getInputStream();
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

            // Crear un bufer para leer los datos descomprimidos
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


            // Leer y mostrar el contenido descomprimido
            while ((len = gzipInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }

            // Cerrar los flujos
            gzipInputStream.close();
            inputStream.close();

            // Convertir los datos descomprimidos a un String
            byte[] uncompressedData = byteArrayOutputStream.toByteArray();
            String dataAsString = new String(uncompressedData, StandardCharsets.UTF_8);

            // Convertir el JSON a una lista de objetos
            ObjectMapper objectMapper = new ObjectMapper();
            List<Clima> climas = objectMapper.readValue(dataAsString, new TypeReference<List<Clima>>(){});
            
            // Establecer los climas en el controlador
            ControladorClimas.getInstance().estableceClimas(climas);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el clima actual", e);
        }
    }
}