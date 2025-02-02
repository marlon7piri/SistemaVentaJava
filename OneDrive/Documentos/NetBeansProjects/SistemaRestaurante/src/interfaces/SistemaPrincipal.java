package interfaces;

import Reportes.Excel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.Conexion;
import modelos.login;
import modelos.productos.Productos;
import modelos.productos.ProductosDAO;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import modelos.clientes.Clientes;
import modelos.clientes.ClientesDAO;
import modelos.detalles.Detalles;
import modelos.detalles.DetallesDAO;
import modelos.empresa.Empresa;
import modelos.empresa.EmpresaDAO;
import modelos.productos.proveedores.ProveedorDAO;
import modelos.productos.proveedores.Proveedores;
import modelos.ventas.Ventas;
import modelos.ventas.VentasDAO;

public class SistemaPrincipal extends javax.swing.JFrame {

    int item;
    double TotalPagar = 0.00;
    DefaultTableModel modelo;
    ProveedorDAO proveedorDao = new ProveedorDAO();
    ClientesDAO clienteDAO = new ClientesDAO();
    ProductosDAO productDao = new ProductosDAO();
    VentasDAO ventasDao = new VentasDAO();
    DetallesDAO detallesDao = new DetallesDAO();
    Detalles detalles = new Detalles();
    Ventas venta = new Ventas();
    Empresa empresa = new Empresa();
    EmpresaDAO empresaDAO = new EmpresaDAO();

    public SistemaPrincipal() {
        initComponents();

        //Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setSize(1200, 600);
        txtIdVenta.setVisible(false);
        txtFechaVenta.setVisible(false);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Centrar la ventana en la pantalla (aunque esté maximizada)
        setLocationRelativeTo(null);

        // Cerrar la aplicación al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        VerProveedores();
        VerClientes();
        VerProductos();
        VerVentas();
        VerEmpresa();

    }

    public void LimpiarTablas(DefaultTableModel modelo) {

        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }

    }

    public void VerClientes() {
        try {

            List<Clientes> clientes = clienteDAO.VerClientes();

            // Obtener el modelo existente de la tabla
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

            if (modelo.getColumnCount() == 0) {
                modelo.addColumn("id");
                modelo.addColumn("nombre");
                modelo.addColumn("ruc");
                modelo.addColumn("telefono");
                modelo.addColumn("direccion");
                modelo.addColumn("razon_social");
            }

            for (Clientes cliente : clientes) {
                Object[] obj = new Object[6];
                obj[0] = cliente.getId();
                obj[1] = cliente.getNombre();
                obj[2] = cliente.getRuc();
                obj[3] = cliente.getTelefono();
                obj[4] = cliente.getDireccion();
                obj[5] = cliente.getRazon_social();

                modelo.addRow(obj);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void VerProveedores() {
        DefaultComboBoxModel<Proveedores> modeloCombo = new DefaultComboBoxModel<>();
        jComboBox1.setModel(modeloCombo);

        List<Proveedores> proveedores = proveedorDao.VerProveedor();

        DefaultTableModel modelo2 = (DefaultTableModel) TablaProveedores.getModel();

        if (modelo2.getColumnCount() == 0) {
            modelo2.addColumn("id");
            modelo2.addColumn("ruc");
            modelo2.addColumn("nombre");
            modelo2.addColumn("telefono");
            modelo2.addColumn("direccion");
            modelo2.addColumn("razon_social");
        }

        for (Proveedores proveedor : proveedores) {

            Object[] fila = new Object[6];
            fila[0] = proveedor.getId();
            fila[1] = proveedor.getRuc();
            fila[2] = proveedor.getNombre();
            fila[3] = proveedor.getTelefono();
            fila[4] = proveedor.getDireccion();
            fila[5] = proveedor.getRazon_social();

            modelo2.addRow(fila);
            modeloCombo.addElement(proveedor);

        }

    }

    public void VerProductos() {

        List<Productos> productos = productDao.VerProductos();

        DefaultTableModel table = (DefaultTableModel) TablaProductos.getModel();

        if (table.getColumnCount() == 0) {
            table.addColumn("id");
            table.addColumn("nombre");
            table.addColumn("cantidad");
            table.addColumn("precio");
            table.addColumn("descripcion");
            table.addColumn("codigo");
            table.addColumn("proveedor");
        }

        for (Productos producto : productos) {
            Object[] fila = new Object[7];
            fila[0] = producto.getId();
            fila[1] = producto.getNombre();
            fila[2] = producto.getCantidad();
            fila[3] = producto.getPrecio();
            fila[4] = producto.getDescripcion();

            fila[5] = producto.getCodigo();

            fila[6] = producto.getProveedor_nombre();

            table.addRow(fila);
        }

    }

    public void VerVentas() {

        List<Ventas> ventas = ventasDao.VerVentas();
        DefaultTableModel modelo = (DefaultTableModel) TablaVentas.getModel();

        if (modelo.getColumnCount() == 0) {
            modelo.addColumn("id");
            modelo.addColumn("cliente");
            modelo.addColumn("vendedor");
            modelo.addColumn("fecha");
            modelo.addColumn("total");

        }

        for (Ventas venta : ventas) {
            Object[] fila = new Object[5];
            fila[0] = venta.getId();
            fila[1] = venta.getCliente();
            fila[2] = venta.getVendedor();
            fila[3] = venta.getFecha();
            fila[4] = venta.getTotal();
            modelo.addRow(fila);
        }
    }

    public void LimpiarCliente() {
        txtIdCliente.setText("");
        txtRucCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtRazonSocialCliente.setText("");
    }

    public void LimpiarProducto() {
        TxtNombreProducto.setText("");
        txtCantidadProductos.setText("");
        txtPrecioProductos.setText("");
        txtCodigoProducto.setText("");
        txtDescripcionProductos.setText("");
    }

    public void LimpiarProveedor() {
        txtIdProveedor.setText("");
        txtRucProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefonoProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtRazonSocialProveedor.setText("");
    }

    public void LimpiarVenta() {
        txtCodigoVenta.setText("");
        txtNombreVenta.setText("");
        txtPrecioVenta.setText("");
        txtCantidadAComprar.setText("");
        txtCantidadVenta.setText("");

    }

    public void LimpiarClienteVenta() {
        txtClienteNombreBusqueda.setText("");
        txtRazonSocialVenta.setText("");
        txtDireccionClienteVenta.setText("");
        txtTelefonoVenta.setText("");
        txtIdClienteVenta.setText("");

    }

    public void VerEmpresa() {
        int id = 1;
        empresa = empresaDAO.VerEmpresa(id);
        System.out.println(empresa);
        labelNombreEmpresa.setText(empresa.getNombre());
        labelDireccionEmpresa.setText(empresa.getDireccion());
        labelRucEmpresa.setText(String.valueOf(empresa.getRuc()));

        labelRazonSocialEmpresa.setText(empresa.getRazon_social());
        labelTelefonoEmpresa.setText(empresa.getTelefono());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        labelNombreEmpresa = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        txtNombreVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableNuevaVenta = new javax.swing.JTable();
        JLabelCantidadVenta = new javax.swing.JLabel();
        txtCantidadAComprar = new javax.swing.JTextField();
        btnEliminarVenta = new javax.swing.JButton();
        txtRucClienteVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtClienteNombreBusqueda = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRazonSocialVenta = new javax.swing.JTextField();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtTelefonoVenta = new javax.swing.JTextField();
        btnCrearVenta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        JlabelTotalPagar = new javax.swing.JLabel();
        txtIdClienteVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtRucCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtRazonSocialCliente = new javax.swing.JTextField();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtPrecioProductos = new javax.swing.JTextField();
        txtCantidadProductos = new javax.swing.JTextField();
        txtDescripcionProductos = new javax.swing.JTextField();
        txtCodigoProducto = new javax.swing.JTextField();
        ScrollPaneProductos = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        JButonActualizarProducto = new javax.swing.JButton();
        txtIdProductos = new javax.swing.JTextField();
        JLabelNombreProducto = new javax.swing.JLabel();
        TxtNombreProducto = new javax.swing.JTextField();
        btnExcel = new javax.swing.JButton();
        txtBusquedaProductoNombre = new javax.swing.JTextField();
        txtBusquedaProductoCodigo = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        btnBuscarProducto = new javax.swing.JButton();
        btnLimpiarFiltros = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtRucProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        txtDireccionProveedor = new javax.swing.JTextField();
        txtRazonSocialProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();
        btnEditarProveedor = new javax.swing.JButton();
        btnEliminarProveedor = new javax.swing.JButton();
        btnNuevoProveedor = new javax.swing.JButton();
        ScrollPaneProveedores = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        txtIdProveedor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        btnImprimirPDF = new javax.swing.JButton();
        btnVerDetallesCuenta = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        txtFechaVenta = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtRucEmpresa = new javax.swing.JTextField();
        txtNombreEmpresa = new javax.swing.JTextField();
        txtDireccionEmpresa = new javax.swing.JTextField();
        txtTelefonoEmpresa = new javax.swing.JTextField();
        txtRazonSocialEmpresa = new javax.swing.JTextField();
        btnActualizarEmpresa = new javax.swing.JButton();
        labelUsuarioRegistrado = new javax.swing.JLabel();
        JlabelDireccionEmpresa = new java.awt.Label();
        JlabelRucEmpresa = new java.awt.Label();
        label1 = new java.awt.Label();
        labelTelefonoEmpresa = new java.awt.Label();
        labelDireccionEmpresa = new java.awt.Label();
        labelRucEmpresa = new java.awt.Label();
        label2 = new java.awt.Label();
        labelRazonSocialEmpresa = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 640));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jButton1.setText("Clientes");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nueva Venta");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Proveedores");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Productos");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Config");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mirc (1).png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(124, 124));
        jLabel2.setMinimumSize(new java.awt.Dimension(124, 124));
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 100));

        jButton22.setText("Ventas");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 660));

        labelNombreEmpresa.setBackground(new java.awt.Color(255, 204, 204));
        labelNombreEmpresa.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        labelNombreEmpresa.setForeground(new java.awt.Color(51, 51, 51));
        labelNombreEmpresa.setText("Punto de Venta");
        getContentPane().add(labelNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 340, 60));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Codigo");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 85, 35));

        jLabel9.setText("Nombre");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 85, -1));

        jLabel10.setText("Stock Disponible");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 100, -1));

        jLabel11.setText("Precio");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 85, -1));

        txtCodigoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoVentaActionPerformed(evt);
            }
        });
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyPressed(evt);
            }
        });
        jPanel8.add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 60, -1));

        txtNombreVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreVentaActionPerformed(evt);
            }
        });
        jPanel8.add(txtNombreVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 100, -1));
        jPanel8.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 50, -1));

        txtPrecioVenta.setEditable(false);
        jPanel8.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 60, -1));

        JTableNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO U.", "TOTAL"
            }
        ));
        jScrollPane1.setViewportView(JTableNuevaVenta);
        if (JTableNuevaVenta.getColumnModel().getColumnCount() > 0) {
            JTableNuevaVenta.getColumnModel().getColumn(0).setPreferredWidth(20);
            JTableNuevaVenta.getColumnModel().getColumn(2).setPreferredWidth(30);
            JTableNuevaVenta.getColumnModel().getColumn(3).setPreferredWidth(30);
            JTableNuevaVenta.getColumnModel().getColumn(4).setPreferredWidth(40);
        }

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 480, 210));

        JLabelCantidadVenta.setText("Cantidad");
        jPanel8.add(JLabelCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        txtCantidadAComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadAComprarActionPerformed(evt);
            }
        });
        txtCantidadAComprar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadAComprarKeyPressed(evt);
            }
        });
        jPanel8.add(txtCantidadAComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 60, -1));

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });
        jPanel8.add(btnEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 60, -1));

        txtRucClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucClienteVentaActionPerformed(evt);
            }
        });
        txtRucClienteVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucClienteVentaKeyPressed(evt);
            }
        });
        jPanel8.add(txtRucClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 40, -1));

        jLabel5.setText("DNI/RUC");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        txtClienteNombreBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteNombreBusquedaActionPerformed(evt);
            }
        });
        jPanel8.add(txtClienteNombreBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 360, 130, -1));

        jLabel6.setText("Nombre");
        jPanel8.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, -1, -1));
        jPanel8.add(txtRazonSocialVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 360, 10, -1));

        txtDireccionClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteVentaActionPerformed(evt);
            }
        });
        jPanel8.add(txtDireccionClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 10, -1));

        txtTelefonoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoVentaActionPerformed(evt);
            }
        });
        jPanel8.add(txtTelefonoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 10, -1));

        btnCrearVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/impresora.png"))); // NOI18N
        btnCrearVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCrearVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearVentaActionPerformed(evt);
            }
        });
        jPanel8.add(btnCrearVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 70, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total a Pagar :");
        jPanel8.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, -1, -1));

        JlabelTotalPagar.setText("---------");
        jPanel8.add(JlabelTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, -1, -1));
        jPanel8.add(txtIdClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 60, -1));

        jLabel4.setText("id del cliente:");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

        jTabbedPane2.addTab("Nueva Venta", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("DNI/RUC:");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel12.setText("Nombre:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel13.setText("Telefono:");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel14.setText("Direccion:");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel15.setText("Razon Social:");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        txtRucCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtRucCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 70, -1));
        jPanel9.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 130, -1));

        txtTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 130, -1));

        txtDireccionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 130, -1));
        jPanel9.add(txtRazonSocialCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 130, -1));

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar.png"))); // NOI18N
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 60, -1));

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-codigo.png"))); // NOI18N
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 60, -1));

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 60, -1));

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 60, -1));

        txtIdCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 20, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Ruc", "Telefono", "Direccion", "Razon Social"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 660, 300));

        jTabbedPane2.addTab("Clientes", jPanel9);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(985, 202));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel21.setText("Codigo:");

        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setText("Descripcion:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel23.setText("Cantidad:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel24.setText("Precio:");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel25.setText("Proveedor:");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "Cantidad", "Precio", "Descripcion", "Codigo", "Proveedor"
            }
        ));
        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        ScrollPaneProductos.setViewportView(TablaProductos);
        if (TablaProductos.getColumnModel().getColumnCount() > 0) {
            TablaProductos.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            TablaProductos.getColumnModel().getColumn(5).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N

        JButonActualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-codigo.png"))); // NOI18N
        JButonActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButonActualizarProductoActionPerformed(evt);
            }
        });

        txtIdProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProductosActionPerformed(evt);
            }
        });

        JLabelNombreProducto.setText("Nombre:");

        TxtNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNombreProductoActionPerformed(evt);
            }
        });

        btnExcel.setText("Descargar Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        txtBusquedaProductoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaProductoCodigoActionPerformed(evt);
            }
        });

        jLabel31.setText("Nombre");

        jLabel32.setText("Codigo");

        btnBuscarProducto.setText("Buscar");
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });

        btnLimpiarFiltros.setText("Limpiar");
        btnLimpiarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFiltrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(26, 26, 26)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton18)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnExcel))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton16)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton17)
                                        .addGap(18, 18, 18)
                                        .addComponent(JButonActualizarProducto)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(529, 529, 529))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(JLabelNombreProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel24))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCantidadProductos)
                                            .addComponent(txtPrecioProductos)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel22))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TxtNombreProducto)
                                            .addComponent(txtDescripcionProductos)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtIdProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)))))
                                .addGap(16, 16, 16)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ScrollPaneProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(txtBusquedaProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusquedaProductoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiarFiltros)
                                .addGap(20, 20, 20))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusquedaProductoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaProductoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(btnBuscarProducto)
                    .addComponent(btnLimpiarFiltros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLabelNombreProducto)
                            .addComponent(TxtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtDescripcionProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtCantidadProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(txtPrecioProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ScrollPaneProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17)
                    .addComponent(JButonActualizarProducto)
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton18)
                    .addComponent(btnExcel))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Productos", jPanel3);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 1000));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("RUC/DNI:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Nombre:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Telefono:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Direccion:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Razon Social:");

        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar.png"))); // NOI18N
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });

        btnEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-codigo.png"))); // NOI18N
        btnEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProveedorActionPerformed(evt);
            }
        });

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N

        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "RUC/DNI", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProveedoresMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TablaProveedoresMouseEntered(evt);
            }
        });
        ScrollPaneProveedores.setViewportView(TablaProveedores);
        if (TablaProveedores.getColumnModel().getColumnCount() > 0) {
            TablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(20);
            TablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(4).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(btnEliminarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDireccionProveedor)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtRucProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRazonSocialProveedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ScrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtDireccionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtRazonSocialProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarProveedor)
                            .addComponent(btnEditarProveedor)))
                    .addComponent(ScrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(btnNuevoProveedor))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Proveedores", jPanel2);

        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vendedor", "Fecha", "Total"
            }
        ));
        TablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaVentas);
        if (TablaVentas.getColumnModel().getColumnCount() > 0) {
            TablaVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            TablaVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            TablaVentas.getColumnModel().getColumn(4).setPreferredWidth(60);
        }

        btnImprimirPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pdf.png"))); // NOI18N

        btnVerDetallesCuenta.setText("Ver detalles");
        btnVerDetallesCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnImprimirPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerDetallesCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimirPDF)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVerDetallesCuenta)
                        .addComponent(txtIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFechaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ventas", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel26.setText("RUC");

        jLabel27.setText("Nombre");

        jLabel28.setText("Direccion");

        jLabel29.setText("Telefono");

        jLabel30.setText("Razon Social");

        btnActualizarEmpresa.setText("Actualizar");
        btnActualizarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizarEmpresa)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDireccionEmpresa)
                            .addComponent(txtRazonSocialEmpresa)
                            .addComponent(txtRucEmpresa)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30))
                                .addGap(0, 100, Short.MAX_VALUE)))
                        .addGap(188, 188, 188)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txtTelefonoEmpresa))))
                .addGap(442, 442, 442))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRucEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefonoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(txtRazonSocialEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnActualizarEmpresa)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Configuracion", jPanel5);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 940, 440));
        getContentPane().add(labelUsuarioRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 20, 10));

        JlabelDireccionEmpresa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        JlabelDireccionEmpresa.setText("Direccion:");
        getContentPane().add(JlabelDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, -1, -1));

        JlabelRucEmpresa.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        JlabelRucEmpresa.setText("Ruc:");
        getContentPane().add(JlabelRucEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 40, -1, -1));

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Telefono:");
        getContentPane().add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, -1));

        labelTelefonoEmpresa.setText("label2");
        getContentPane().add(labelTelefonoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, -1, -1));

        labelDireccionEmpresa.setText("label2");
        getContentPane().add(labelDireccionEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        labelRucEmpresa.setText("label3");
        getContentPane().add(labelRucEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 40, -1, -1));

        label2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label2.setText("Razon Social:");
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        labelRazonSocialEmpresa.setText("label3");
        getContentPane().add(labelRazonSocialEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jTabbedPane2.setSelectedIndex(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtRucClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucClienteActionPerformed

    private void txtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteActionPerformed

    private void txtDireccionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteActionPerformed

    private void JButonActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButonActualizarProductoActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdProductos.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
            return;

        }
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro desea actualizar el producto " + TxtNombreProducto.getText());

        if (respuesta == 0) {
            modelo = (DefaultTableModel) TablaProductos.getModel();
            Productos producto = new Productos();

            int id = Integer.parseInt(txtIdProductos.getText());
            String nombre = TxtNombreProducto.getText();
            double precio = Double.parseDouble(txtPrecioProductos.getText());
            int cantidad = Integer.parseInt(txtCantidadProductos.getText());
            String codigo = txtCodigoProducto.getText();
            String descripcion = txtDescripcionProductos.getText();

            Proveedores proveedor = (Proveedores) jComboBox1.getSelectedItem();
            int proveedor_id = proveedor.getId();

            producto.setId(id);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            producto.SetCodigo(codigo);
            producto.setDescripcion(descripcion);
            producto.setProveedor_id(proveedor_id);

            boolean res = productDao.ActualizarProducto(id, producto);

            if (res == true) {
                JOptionPane.showMessageDialog(null, "Producto actualizado");
                LimpiarTablas(modelo);
                VerProductos();
                LimpiarProducto();
            } else {
                JOptionPane.showMessageDialog(null, "Algo paso,intente de nuevo");

            }
        }
    }//GEN-LAST:event_JButonActualizarProductoActionPerformed

    private void txtDireccionClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteVentaActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed

        try {

            PreparedStatement pst = clienteDAO.CrearCliente();
            modelo = (DefaultTableModel) jTable1.getModel();
            pst.setString(1, txtNombreCliente.getText());
            pst.setInt(2, Integer.parseInt(txtRucCliente.getText()));
            pst.setString(3, txtTelefonoCliente.getText());
            pst.setString(4, txtDireccionCliente.getText());
            pst.setString(5, txtRazonSocialCliente.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Cliente Registrado correctamente");
            LimpiarTablas(modelo);
            VerClientes();

        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        try {

            Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("INSERT INTO proveedores  (ruc,nombre,telefono,direccion,razon_social)VALUES (?,?,?,?,?)");

            pst.setInt(1, Integer.parseInt(txtRucProveedor.getText()));
            pst.setString(2, txtNombreProveedor.getText());
            pst.setString(3, txtTelefonoProveedor.getText());
            pst.setString(4, txtDireccionProveedor.getText());
            pst.setString(5, txtRazonSocialProveedor.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Proveedor Registrado correctamente");

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        VerProveedores();
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClienteActionPerformed
        // TODO add your handling code here:

        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
            return;

        }
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro desea actualizar el cliente " + txtNombreCliente.getText());

        if (respuesta == 0) {
            modelo = (DefaultTableModel) jTable1.getModel();
            Clientes cliente = new Clientes();
            int id = Integer.parseInt(txtIdCliente.getText());
            String nombre = txtNombreCliente.getText();
            int ruc = Integer.parseInt(txtRucCliente.getText());
            String telefono = txtTelefonoCliente.getText();
            String direccion = txtDireccionCliente.getText();
            String razon_social = txtRazonSocialCliente.getText();

            cliente.setId(id);
            cliente.setNombre(nombre);
            cliente.setRuc(ruc);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);
            cliente.setRazon_social(razon_social);

            boolean res = clienteDAO.ActualizarCliente(cliente.getId(), cliente);

            if (res == true) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado");
                LimpiarTablas(modelo);
                VerClientes();
                LimpiarCliente();
            } else {
                JOptionPane.showMessageDialog(null, "Algo paso,intente de nuevo");

            }
        }


    }//GEN-LAST:event_btnEditarClienteActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void TxtNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNombreProductoActionPerformed

    }//GEN-LAST:event_TxtNombreProductoActionPerformed


    private void txtIdProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProductosActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        try {

            PreparedStatement pst = productDao.CrearProducto();

            Proveedores seleccionado = (Proveedores) jComboBox1.getSelectedItem();
            modelo = (DefaultTableModel) TablaProductos.getModel();

            pst.setString(1, TxtNombreProducto.getText());
            pst.setInt(2, Integer.parseInt(txtCantidadProductos.getText()));
            pst.setDouble(3, Double.parseDouble(txtPrecioProductos.getText()));
            pst.setString(4, txtDescripcionProductos.getText());
            pst.setString(5, txtCodigoProducto.getText());
            pst.setInt(6, seleccionado.getId());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Producto creado.");

        } catch (SQLException e) {
            System.out.println("Error creando productos");
        }
        LimpiarTablas(modelo);
        VerProductos();


    }//GEN-LAST:event_jButton16ActionPerformed

    private void TablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedoresMouseClicked
        // TODO add your handling code here:

        int fila = TablaProveedores.rowAtPoint(evt.getPoint());

        txtIdProveedor.setText(TablaProveedores.getValueAt(fila, 0).toString());
        txtNombreProveedor.setText(TablaProveedores.getValueAt(fila, 1).toString());
        txtRucProveedor.setText(TablaProveedores.getValueAt(fila, 2).toString());
        txtTelefonoProveedor.setText(TablaProveedores.getValueAt(fila, 3).toString());
        txtDireccionProveedor.setText(TablaProveedores.getValueAt(fila, 4).toString());
        txtRazonSocialProveedor.setText(TablaProveedores.getValueAt(fila, 5).toString());

    }//GEN-LAST:event_TablaProveedoresMouseClicked

    private void TablaProveedoresMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedoresMouseEntered


    }//GEN-LAST:event_TablaProveedoresMouseEntered

    private void txtClienteNombreBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteNombreBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteNombreBusquedaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int fila = jTable1.rowAtPoint(evt.getPoint());

        txtIdCliente.setText(jTable1.getValueAt(fila, 0).toString());
        txtNombreCliente.setText(jTable1.getValueAt(fila, 1).toString());
        txtRucCliente.setText(jTable1.getValueAt(fila, 2).toString());
        txtTelefonoCliente.setText(jTable1.getValueAt(fila, 3).toString());
        txtDireccionCliente.setText(jTable1.getValueAt(fila, 4).toString());
        txtRazonSocialCliente.setText(jTable1.getValueAt(fila, 5).toString());


    }//GEN-LAST:event_jTable1MouseClicked

    private void btnEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProveedorActionPerformed
        // TODO add your handling code here:

        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione un proveedor");
            return;

        }
        int respuesta = JOptionPane.showConfirmDialog(null, "Seguro desea actualizar el proveedor " + txtNombreProveedor.getText());

        if (respuesta == 0) {
            modelo = (DefaultTableModel) TablaProveedores.getModel();
            Proveedores proveedor = new Proveedores();
            int id = Integer.parseInt(txtIdProveedor.getText());
            String nombre = txtNombreProveedor.getText();
            int ruc = Integer.parseInt(txtRucProveedor.getText());
            String telefono = txtTelefonoProveedor.getText();
            String direccion = txtDireccionProveedor.getText();
            String razon_social = txtRazonSocialProveedor.getText();

            proveedor.setId(id);
            proveedor.setNombre(nombre);
            proveedor.setRuc(ruc);
            proveedor.setTelefono(telefono);
            proveedor.setDireccion(direccion);
            proveedor.setRazon_social(razon_social);

            boolean res = proveedorDao.ActualizarProveedor(proveedor.getId(), proveedor);

            if (res == true) {
                JOptionPane.showMessageDialog(null, "Proveedor actualizado");
                LimpiarTablas(modelo);
                VerProveedores();
                LimpiarProveedor();
            } else {
                JOptionPane.showMessageDialog(null, "Algo paso,intente de nuevo");

            }
        }
    }//GEN-LAST:event_btnEditarProveedorActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:

        String id = txtIdCliente.getText();
        modelo = (DefaultTableModel) jTable1.getModel();

        if (!"".equals(id)) {

            int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar el cliente?");

            if (respuesta == 0) {
                boolean res = clienteDAO.EliminarCliente(Integer.parseInt(id));
                if (res == true) {
                    JOptionPane.showMessageDialog(null, "Cliente eliminado");
                    LimpiarTablas(modelo);
                    VerClientes();

                } else {
                    JOptionPane.showMessageDialog(null, "Algo sucedio, intente de nuevo");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione algun cliente");
            }
        }


    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        // TODO add your handling code here:

        int fila = TablaProductos.rowAtPoint(evt.getPoint());

        txtIdProductos.setText(TablaProductos.getValueAt(fila, 0).toString());
        TxtNombreProducto.setText(TablaProductos.getValueAt(fila, 1).toString());
        txtCantidadProductos.setText(TablaProductos.getValueAt(fila, 2).toString());
        txtPrecioProductos.setText(TablaProductos.getValueAt(fila, 3).toString());
        txtDescripcionProductos.setText(TablaProductos.getValueAt(fila, 4).toString());

        txtCodigoProducto.setText(TablaProductos.getValueAt(fila, 5).toString());

        jComboBox1.setSelectedItem(TablaProductos.getValueAt(fila, 6).toString());


    }//GEN-LAST:event_TablaProductosMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:

        int id = Integer.parseInt(txtIdProductos.getText());
        modelo = (DefaultTableModel) TablaProductos.getModel();

        int respuesta = JOptionPane.showConfirmDialog(null, "Desea eliminar el producto");

        if (respuesta == 0) {
            boolean res = productDao.EliminarProducto(id);
            if (res == true) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                LimpiarTablas(modelo);
                VerProductos();

            } else {
                JOptionPane.showMessageDialog(null, "Algo paso, intente de nuevo");
            }
        }


    }//GEN-LAST:event_jButton17ActionPerformed

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:

        String sql = "SELECT p.codigo, p.nombre, "
                + "p.precio, p.cantidad,pr.nombre AS proveedor FROM productos p "
                + "INNER JOIN proveedores pr ON pr.id = p.proveedor_id";

        Excel.reporte(sql, "productos");


    }//GEN-LAST:event_btnExcelActionPerformed

    private void txtCodigoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtCodigoVenta.getText())) {
                String cod = txtCodigoVenta.getText();
                Productos producto = productDao.BuscarProducto(cod);

                if (producto.getNombre() != null) {
                    txtNombreVenta.setText("" + producto.getNombre());
                    txtPrecioVenta.setText("" + producto.getPrecio());
                    txtCantidadVenta.setText("" + producto.getCantidad());
                    txtCantidadAComprar.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro");
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();

                }

            }

        }

    }//GEN-LAST:event_txtCodigoVentaKeyPressed

    private void txtCantidadAComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadAComprarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadAComprarActionPerformed

    private void txtNombreVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreVentaActionPerformed

    private void txtCodigoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoVentaActionPerformed

    private void txtCantidadAComprarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadAComprarKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!"".equals(txtCantidadAComprar.getText())) {
                String codigo = txtCodigoVenta.getText();
                String nombre = txtNombreVenta.getText();
                int stock = Integer.parseInt(txtCantidadVenta.getText());
                int cantidad = Integer.parseInt(txtCantidadAComprar.getText());
                double precio = Double.parseDouble(txtPrecioVenta.getText());
                double total = cantidad * precio;
                item = item + 1;
                modelo = (DefaultTableModel) JTableNuevaVenta.getModel();
                if (stock >= cantidad) {

                    for (int i = 0; i < JTableNuevaVenta.getRowCount(); i++) {

                        if (JTableNuevaVenta.getValueAt(i, 1).equals(txtNombreVenta.getText())) {
                            JOptionPane.showMessageDialog(null, "El producto ya existe");
                            return;
                        }
                    }

                    ArrayList lista = new ArrayList<>();
                    lista.add(item);
                    lista.add(codigo);
                    lista.add(nombre);
                    lista.add(cantidad);
                    lista.add(precio);
                    lista.add(total);

                    Object[] obj = new Object[5];
                    obj[0] = lista.get(1);
                    obj[1] = lista.get(2);
                    obj[2] = lista.get(3);
                    obj[3] = lista.get(4);
                    obj[4] = lista.get(5);

                    modelo.addRow(obj);
                    TablaVentas.setModel(modelo);
                    CalcularTotalPagar();
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();

                } else {
                    JOptionPane.showMessageDialog(null, "No hay productos en stock");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Introduce la cantidad");
            }

        }
    }//GEN-LAST:event_txtCantidadAComprarKeyPressed

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        // TODO add your handling code here:

        modelo = (DefaultTableModel) JTableNuevaVenta.getModel();
        int filaSelected = JTableNuevaVenta.getSelectedRow();
        if (filaSelected >= 0) {
            modelo.removeRow(JTableNuevaVenta.getSelectedRow());
            CalcularTotalPagar();
            txtCodigoVenta.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una producto a eliminar de la venta");
        }


    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void txtRucClienteVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucClienteVentaKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String ruc = txtRucClienteVenta.getText();
            if (!"".equals(ruc)) {
                Clientes cliente = clienteDAO.BuscarCliente(Integer.parseInt(ruc));
                if (cliente.getNombre() != null) {
                    txtClienteNombreBusqueda.setText(cliente.getNombre());
                    txtRazonSocialVenta.setText(cliente.getRazon_social());
                    txtDireccionClienteVenta.setText(cliente.getDireccion());
                    txtTelefonoVenta.setText(cliente.getTelefono());
                    txtIdClienteVenta.setText(String.valueOf(cliente.getId()));
                } else {
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Escriba un ruc o dni");
                txtRucClienteVenta.setText("");
                txtClienteNombreBusqueda.setText("");
            }
        }
    }//GEN-LAST:event_txtRucClienteVentaKeyPressed

    private void txtIdClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdClienteActionPerformed

    private void btnCrearVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearVentaActionPerformed
        // TODO add your handling code here:

        RegistrarVenta();
        RegistrarDetalles();
        ActualizarStock();
        modelo = (DefaultTableModel) TablaVentas.getModel();
        DefaultTableModel modelo2 = (DefaultTableModel) TablaProductos.getModel();

        LimpiarTablas(modelo);

        LimpiarTablas(modelo2);
        VerProductos();
        LimpiarClienteVenta();
        VerVentas();

    }//GEN-LAST:event_btnCrearVentaActionPerformed

    private void txtTelefonoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoVentaActionPerformed

    private void txtRucClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucClienteVentaActionPerformed

    private void txtBusquedaProductoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaProductoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaProductoCodigoActionPerformed

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
        // TODO add your handling code here:

        String cod = txtBusquedaProductoCodigo.getText();
        String nombre = txtBusquedaProductoNombre.getText();

        List<Productos> productos = productDao.FiltrarProductos(cod, nombre);

        DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();

        LimpiarTablas(modelo);
        for (Productos producto : productos) {
            Object[] obj = new Object[7];
            obj[0] = producto.getId();
            obj[1] = producto.getNombre();
            obj[2] = producto.getCantidad();
            obj[3] = producto.getPrecio();
            obj[4] = producto.getDescripcion();
            obj[5] = producto.getCodigo();
            obj[6] = producto.getProveedor_nombre();
            modelo.addRow(obj);

        }


    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnLimpiarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFiltrosActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();

        LimpiarTablas(modelo);
        VerProductos();
        txtBusquedaProductoCodigo.setText("");
        txtBusquedaProductoNombre.setText("");
    }//GEN-LAST:event_btnLimpiarFiltrosActionPerformed

    private void btnVerDetallesCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesCuentaActionPerformed
        // TODO add your handling code here:

        new VistaDetalles(txtFechaVenta.getText(), txtIdVenta.getText()).setVisible(true);
    }//GEN-LAST:event_btnVerDetallesCuentaActionPerformed

    private void TablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasMouseClicked
        // TODO add your handling code here:

        int filaSelected = TablaVentas.rowAtPoint(evt.getPoint());

        if (filaSelected >= 0) {
            int id = Integer.parseInt(TablaVentas.getValueAt(filaSelected, 0).toString());
            String fecha = TablaVentas.getValueAt(filaSelected, 3).toString();

            txtIdVenta.setText(String.valueOf(id));
            txtFechaVenta.setText(fecha);

        }
    }//GEN-LAST:event_TablaVentasMouseClicked

    private void btnActualizarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpresaActionPerformed
        // TODO add your handling code here:

        String nombre = txtNombreEmpresa.getText();
        int ruc = Integer.parseInt(txtRucEmpresa.getText());
        String direccion = txtDireccionEmpresa.getText();
        String razon_social = txtRazonSocialEmpresa.getText();
        String telefono = txtTelefonoEmpresa.getText();

        empresa.setRuc(ruc);
        empresa.setNombre(nombre);
        empresa.setDireccion(direccion);
        empresa.setRazon_social(razon_social);
        empresa.setTelefono(telefono);

        empresaDAO.CrearEmpresa(empresa);


    }//GEN-LAST:event_btnActualizarEmpresaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SistemaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButonActualizarProducto;
    private javax.swing.JLabel JLabelCantidadVenta;
    private javax.swing.JLabel JLabelNombreProducto;
    private javax.swing.JTable JTableNuevaVenta;
    private java.awt.Label JlabelDireccionEmpresa;
    private java.awt.Label JlabelRucEmpresa;
    private javax.swing.JLabel JlabelTotalPagar;
    private javax.swing.JScrollPane ScrollPaneProductos;
    private javax.swing.JScrollPane ScrollPaneProveedores;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTable TablaProveedores;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JTextField TxtNombreProducto;
    private javax.swing.JButton btnActualizarEmpresa;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnCrearVenta;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnImprimirPDF;
    private javax.swing.JButton btnLimpiarFiltros;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton btnVerDetallesCuenta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<Proveedores> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label labelDireccionEmpresa;
    private javax.swing.JLabel labelNombreEmpresa;
    private java.awt.Label labelRazonSocialEmpresa;
    private java.awt.Label labelRucEmpresa;
    private java.awt.Label labelTelefonoEmpresa;
    private javax.swing.JLabel labelUsuarioRegistrado;
    private javax.swing.JTextField txtBusquedaProductoCodigo;
    private javax.swing.JTextField txtBusquedaProductoNombre;
    private javax.swing.JTextField txtCantidadAComprar;
    private javax.swing.JTextField txtCantidadProductos;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtClienteNombreBusqueda;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDescripcionProductos;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionEmpresa;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdClienteVenta;
    private javax.swing.JTextField txtIdProductos;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreEmpresa;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtPrecioProductos;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonSocialCliente;
    private javax.swing.JTextField txtRazonSocialEmpresa;
    private javax.swing.JTextField txtRazonSocialProveedor;
    private javax.swing.JTextField txtRazonSocialVenta;
    private javax.swing.JTextField txtRucCliente;
    private javax.swing.JTextField txtRucClienteVenta;
    private javax.swing.JTextField txtRucEmpresa;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoEmpresa;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTelefonoVenta;
    // End of variables declaration//GEN-END:variables

    private void CalcularTotalPagar() {

        TotalPagar = 0.00;

        for (int i = 0; i < JTableNuevaVenta.getModel().getRowCount(); i++) {

            double can = Double.parseDouble(String.valueOf(JTableNuevaVenta.getValueAt(i, 4)));

            TotalPagar = TotalPagar + can;

        }
        JlabelTotalPagar.setText(String.format("%.2f", TotalPagar));
    }

    private void RegistrarVenta() {
        if (TotalPagar != 0 && !"".equals(txtIdClienteVenta.getText())) {
            venta.setTotal(TotalPagar);
            venta.setCliente(Integer.parseInt(txtIdClienteVenta.getText()));
            venta.setVendedor("Mi empresa");

            boolean res = ventasDao.CrearVenta(venta);

            if (res == true) {
                JOptionPane.showMessageDialog(null, "Venta creada");
            } else {
                JOptionPane.showMessageDialog(null, "Error, intente de nuevo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tiene productos que vender o ningun cliente seleccionado");
        }

    }

    private void RegistrarDetalles() {

        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            int codigo = Integer.parseInt(TablaVentas.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(TablaVentas.getValueAt(i, 2).toString());
            double precio = Double.parseDouble(TablaVentas.getValueAt(i, 3).toString());
            int id;
            id = ventasDao.getIdVenta();

            detalles.setCodigo_prod(codigo);
            detalles.setPrecio(precio);
            detalles.setCantidad(cantidad);
            detalles.setId(id);
            detallesDao.RegistrarDetalles(detalles);

        }
    }

    private void ActualizarStock() {

        for (int i = 0; i < TablaVentas.getRowCount(); i++) {

            String codigo = TablaVentas.getValueAt(i, 0).toString();
            int cantidad = Integer.parseInt(TablaVentas.getValueAt(i, 2).toString());

            Productos producto = productDao.BuscarProducto(codigo);
            int StockActual = producto.getCantidad() - cantidad;

            ventasDao.ActualizarStock(StockActual, Integer.parseInt(codigo));

        }
    }
}
