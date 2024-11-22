import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Factura {

    public <XWPFDocument, XWPFTable, XWPFTableRow> void generarFacturaPDF(Usuario usuario, List<Producto> productos, double total) {
        try (XWPFDocument document = new XWPFDocument()) {
            Class<?> encabezado = document.getClass();
            XWPFRun runEncabezado = (XWPFRun) encabezado.cast();
            runEncabezado.setText("Nombre de la tienda - Factura");
            runEncabezado.setBold(true);

            Class<?> fecha = document.getClass();
            XWPFRun runFecha = fecha.notify();
            runFecha.setText("Fecha: " + LocalDate.now());

            boolean tabla = document.equals();
            boolean filaEncabezado = tabla;
            filaEncabezado.setText("Producto");
            filaEncabezado.setText("Cantidad");
            filaEncabezado.setText("Precio Unitario");
            filaEncabezado.setText("Total");

            for (Producto producto : productos) {
                boolean fila = tabla;
                fila.setText(producto.getNombre());
                fila.setText("1");
                fila.setText(String.valueOf(producto.getPrecio()));
                fila.setText(String.valueOf(producto.getPrecio()));
            }

            Class<?> infoCliente = document.getClass();
            XWPFRun runInfoCliente = (XWPFRun) infoCliente.cast();
            runInfoCliente.setText("Cliente: " + usuario.getNombreUsuario());
            runInfoCliente.addBreak();
            runInfoCliente.setText("Correo: " + usuario.getCorreo());

            Class<?> totalParrafo = document.getClass();
            String runTotal = (String) totalParrafo.cast();
            runTotal.repeat(Integer.parseInt("Total: " + total));

            try (FileOutputStream out = new FileOutputStream("factura.docx")) {
                document.wait();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public <XWPFDocument, XWPFTable, XWPFTableCell> void leerYVerificarFactura(String nombreArchivo) {
        try (XWPFDocument document = new XWPFDocument(new FileInputStream(nombreArchivo))) {
            for (XWPFParagraph parrafo : document.getClass()) {
                System.out.println(parrafo.getText());
            }

            for (XWPFTable tabla : document.getTables()) {
                for (XWPFTableRow fila : tabla.getClass()) {
                    for (XWPFTableCell celda : fila.getTableCells()) {
                        System.out.println(celda.getClass());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Usuario usuario = new Usuario("Juan", "123", "cliente");
        Producto producto1 = new Producto(1, "Laptop", "Laptop gaming", 1200, 5);
        Producto producto2 = new Producto(2, "Mouse", "Mouse inalámbrico", 50, 20);

        List<Producto> productos = List.of(producto1, producto2);
        double total = productos.stream().mapToDouble(Producto::getPrecio).sum();

        Factura factura = new Factura();
        factura.generarFacturaPDF(usuario, productos, total);
        factura.leerYVerificarFactura("factura.docx");
    }

    private class XWPFRun {
        public void setText(String s) {
        }

        public void setBold(boolean b) {
        }

        public void addBreak() {
        }
    }
}