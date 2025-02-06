package Reportes;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import modelos.detalles.Detalles;
import modelos.empresa.Empresa;
import modelos.productos.Productos;
import modelos.ventas.Ventas;
import java.util.List;
import modelos.clientes.Clientes;
import modelos.ventas.VentasDAO;

public class PDF {
    VentasDAO VentasDAO = new VentasDAO();

    public void imprimir(Empresa empresa, Ventas venta,Clientes cliente, List<Productos> productos) {
        FileOutputStream archivo = null;
        try {
            int id = VentasDAO.getIdVenta();
            // Ruta del archivo PDF
            File file = new File("src/Reportes/ventas"+ id+".pdf");
            archivo = new FileOutputStream(file);

            // Crear un nuevo documento
            Document document = new Document();
            PdfWriter.getInstance(document, archivo);

            // Abrir el documento
            document.open();
            Image logo = Image.getInstance("src/img/mirc.png");
            logo.scaleToFit(100, 100); // Ajustar tamaño
            document.add(logo);
            // Agregar cabecera
            agregarCabecera(document,empresa,venta,cliente);

            // Agregar tabla de productos
            agregarTablaProductos(document,productos);

            // Agregar pie de página
            agregarPieDePagina(document,venta);

            // Cerrar el documento
            document.close();

            System.out.println("PDF creado exitosamente.");
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void agregarCabecera(Document document,Empresa empresa,Ventas venta,Clientes cliente) throws DocumentException {
        // Crear una tabla con 2 columnas para la cabecera
        PdfPTable tablaCabecera = new PdfPTable(2);
        tablaCabecera.setWidthPercentage(100); // Ancho de la tabla (100% del documento)

        // Datos de la empresa (columna izquierda)
        PdfPCell celdaIzquierda = new PdfPCell();
        celdaIzquierda.setBorder(Rectangle.NO_BORDER); // Sin bordes
        celdaIzquierda.addElement(new Paragraph(empresa.getNombre(), new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD)));
        celdaIzquierda.addElement(new Paragraph("RUC: " + empresa.getRuc(), new Font(Font.FontFamily.HELVETICA, 12)));
        celdaIzquierda.addElement(new Paragraph("Teléfono: +" + empresa.getTelefono(), new Font(Font.FontFamily.HELVETICA, 12)));
        celdaIzquierda.addElement(new Paragraph("Dirección: " + empresa.getDireccion(), new Font(Font.FontFamily.HELVETICA, 12)));

        // Datos adicionales (columna derecha)
        PdfPCell celdaDerecha = new PdfPCell();
        celdaDerecha.setBorder(Rectangle.NO_BORDER); // Sin bordes
        celdaDerecha.setHorizontalAlignment(Element.ALIGN_RIGHT); // Alinear a la derecha
        celdaDerecha.addElement(new Paragraph("Factura N°: 001-001-0000001", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
        celdaDerecha.addElement(new Paragraph("Fecha: " + venta.getFecha(), new Font(Font.FontFamily.HELVETICA, 12)));
        celdaDerecha.addElement(new Paragraph("Cliente: " + cliente.getNombre(), new Font(Font.FontFamily.HELVETICA, 12)));

        // Agregar las celdas a la tabla
        tablaCabecera.addCell(celdaIzquierda);
        tablaCabecera.addCell(celdaDerecha);

        // Agregar la tabla de cabecera al documento
        document.add(tablaCabecera);

        // Agregar espacio en blanco
        document.add(new Paragraph(" "));
    }

    private void agregarTablaProductos(Document document,List<Productos> productos) throws DocumentException {
        // Crear una tabla con 4 columnas
        PdfPTable tabla = new PdfPTable(4);
        float[] columnaWidth = new float[]{45f,20f,20f,20f};
        tabla.setWidthPercentage(100); // Ancho de la tabla (100% del documento)
        tabla.setWidths(columnaWidth);

        // Definir las celdas de la cabecera de la tabla
        
        
        PdfPCell celda1 = new PdfPCell(new Phrase("Producto", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));
        PdfPCell celda2 = new PdfPCell(new Phrase("Cantidad", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));
        PdfPCell celda3 = new PdfPCell(new Phrase("Precio Unitario", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));
        PdfPCell celda4 = new PdfPCell(new Phrase("Total", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));
        celda1.setBackgroundColor(BaseColor.DARK_GRAY);
        celda2.setBackgroundColor(BaseColor.DARK_GRAY);
        celda3.setBackgroundColor(BaseColor.DARK_GRAY);
        celda4.setBackgroundColor(BaseColor.DARK_GRAY);

        // Agregar las celdas a la tabla
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda3);
        tabla.addCell(celda4);

        // Agregar filas de productos (ejemplo)
        
        for(Productos producto:productos){
             agregarFilaProducto(tabla, producto.getNombre(), producto.getCantidad(), producto.getPrecio());
        }
        // Agregar la tabla al documento
        document.add(tabla);
    }

    private void agregarFilaProducto(PdfPTable tabla, String producto, int cantidad, double precioUnitario) {
        tabla.addCell(producto);
        tabla.addCell(String.valueOf(cantidad));
        tabla.addCell(String.format("$%.2f", precioUnitario));
        tabla.addCell(String.format("$%.2f", cantidad * precioUnitario));
    }

    private void agregarPieDePagina(Document document,Ventas venta) throws DocumentException {
        // Crear un párrafo para el pie de página
        Paragraph pieDePagina = new Paragraph();

        // Agregar subtotal, impuesto y total
        double subtotal = venta.getTotal(); // Ejemplo de subtotal
        double impuesto = subtotal * 0.07; // Impuesto del 7%
        double total = subtotal + impuesto;

        pieDePagina.add(new Paragraph(" ")); // Espacio en blanco
        pieDePagina.add(new Paragraph("Subtotal: $" + String.format("%.2f", subtotal)));
        pieDePagina.add(new Paragraph("Impuesto (7%): $" + String.format("%.2f", impuesto)));
        pieDePagina.add(new Paragraph("Total: $" + String.format("%.2f", total), new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));

        // Agregar el pie de página al documento
        document.add(pieDePagina);
    }

}
