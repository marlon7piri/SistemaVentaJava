/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
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
import modelos.productos.proveedores.ProveedorDAO;
import modelos.productos.proveedores.Proveedores;

/**
 *
 * @author marlo
 */
public class SistemaPrincipal extends javax.swing.JFrame {

    public SistemaPrincipal() {
        initComponents();

        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setSize(window.width, window.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Centrar la ventana en la pantalla (aunque esté maximizada)
        setLocationRelativeTo(null);

        // Cerrar la aplicación al cerrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        VerProveedores();
        VerClientes();
        VerProductos();

    }

    public void VerClientes() {
        try {
            Connection con = Conexion.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from clientes");

            ResultSet rs = pst.executeQuery();

            DefaultTableModel modelo = new DefaultTableModel();

            TablaClientes = new JTable(modelo);
            ScrollPaneTablaCliente.setViewportView(TablaClientes);
            modelo.addColumn("id");
            modelo.addColumn("dni");
            modelo.addColumn("nombre");
            modelo.addColumn("telefono");
            modelo.addColumn("direccion");
            modelo.addColumn("razon");
            //modelo.addColumn("fecha-registro");

            while (rs.next()) {

                Object[] fila = new Object[6];

                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);

                }
                modelo.addRow(fila);

            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void VerProveedores() {
        DefaultComboBoxModel<Proveedores> modeloCombo = new DefaultComboBoxModel<>();
        jComboBox1.setModel(modeloCombo);

        ProveedorDAO proveedorDao = new ProveedorDAO();
        List<Proveedores> proveedores = proveedorDao.VerProveedor();

        DefaultTableModel modelo2 = new DefaultTableModel();

        TablaProveedores = new JTable(modelo2);
        ScrollPaneProveedores.setViewportView(TablaProveedores);
        modelo2.addColumn("id");
        modelo2.addColumn("ruc");
        modelo2.addColumn("nombre");
        modelo2.addColumn("telefono");
        modelo2.addColumn("direccion");
        modelo2.addColumn("razon_social");

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

        ProductosDAO productDao = new ProductosDAO();
        List<Productos> productos = productDao.VerProductos();

        DefaultTableModel table = new DefaultTableModel();
        TablaProductos = new JTable(table);
        jScrollPane4.setViewportView(TablaProductos);

        table.addColumn("id");
        table.addColumn("nombre");
        table.addColumn("descripcion");
        table.addColumn("cantidad");
        table.addColumn("precio");
        table.addColumn("codigo");
        table.addColumn("id_prove");
        table.addColumn("proveedor");

        for (Productos producto : productos) {
            Object[] fila = new Object[8];
            fila[0] = producto.getId();
            fila[1] = producto.getNombre();
            fila[2] = producto.getDescripcion();
            fila[3] = producto.getCantidad();
            fila[4] = producto.getPrecio();
            fila[5] = producto.getCodigo();
            fila[6] = producto.getProveedor_id();
            fila[7] = producto.getProveedor_nombre();

            table.addRow(fila);
        }

    }

    public void Limpiar() {
        txtRucCliente.setText("");
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtDireccionCliente.setText("");
        txtRazonSocialCliente.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        txtCodigoVenta = new javax.swing.JTextField();
        txtDescripcionVenta = new javax.swing.JTextField();
        txtCantidadVenta = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableNuevaVenta = new javax.swing.JTable();
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
        ScrollPaneTablaCliente = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        txtIdProductos = new javax.swing.JTextField();
        JLabelNombreProducto = new javax.swing.JLabel();
        TxtNombreProducto = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        btnImprimirPDF = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        btnImprimirNuevaVenta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        txtClienteVenta = new javax.swing.JTextField();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtTelefonoVenta = new javax.swing.JTextField();
        labelUsuarioRegistrado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 640));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

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
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton22)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 220, 720));

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Punto de Venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 380, 60));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Codigo");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 85, 35));

        jLabel9.setText("descripcion");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 45, 85, -1));

        jLabel10.setText("cantidad");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 45, 85, -1));

        jLabel11.setText("precio");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 45, 85, -1));

        btnEliminarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel8.add(btnEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));
        jPanel8.add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 81, 60, -1));
        jPanel8.add(txtDescripcionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 81, 100, -1));
        jPanel8.add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 81, 50, -1));

        txtPrecioVenta.setEditable(false);
        jPanel8.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 81, 60, -1));

        JTableNuevaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "descripcion", "cantidad", "precio", "total"
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

        jTabbedPane2.addTab("Nueva Venta", jPanel8);

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("DNI/RUC:");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel12.setText("Nombre:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel13.setText("Telefono:");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel14.setText("Direccion:");
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel15.setText("Razon Social:");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        txtRucCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtRucCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 70, -1));
        jPanel9.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 130, -1));

        txtTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 130, -1));

        txtDireccionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteActionPerformed(evt);
            }
        });
        jPanel9.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 130, -1));
        jPanel9.add(txtRazonSocialCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 130, -1));

        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI/RUC", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ));
        ScrollPaneTablaCliente.setViewportView(TablaClientes);
        if (TablaClientes.getColumnModel().getColumnCount() > 0) {
            TablaClientes.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaClientes.getColumnModel().getColumn(1).setPreferredWidth(60);
            TablaClientes.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaClientes.getColumnModel().getColumn(3).setPreferredWidth(60);
            TablaClientes.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        jPanel9.add(ScrollPaneTablaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 510, 210));

        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar.png"))); // NOI18N
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        btnEditarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-codigo.png"))); // NOI18N
        btnEditarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEditarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.add(btnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });
        jPanel9.add(btnNuevoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));
        jPanel9.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 20, -1));

        jTabbedPane2.addTab("Clientes", jPanel9);

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

        btnEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N

        btnNuevoProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N

        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUC/DNI", "Nombre", "Telefono", "Direccion", "Razon Social"
            }
        ));
        ScrollPaneProveedores.setViewportView(TablaProveedores);
        if (TablaProveedores.getColumnModel().getColumnCount() > 0) {
            TablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaProveedores.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRazonSocialProveedor)
                            .addComponent(txtDireccionProveedor)
                            .addComponent(txtNombreProveedor)
                            .addComponent(txtTelefonoProveedor)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGuardarProveedor)
                            .addComponent(btnEliminarProveedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarProveedor)
                            .addComponent(btnNuevoProveedor))
                        .addGap(19, 19, 19)))
                .addGap(18, 18, 18)
                .addComponent(ScrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
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
                    .addComponent(btnEditarProveedor))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarProveedor)
                    .addComponent(btnNuevoProveedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(ScrollPaneProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Proveedores", jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(785, 202));

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
                "Codigo", "Codigo", "Cantidad", "Precio", "Descripcion", "Proveedor"
            }
        ));
        jScrollPane4.setViewportView(TablaProductos);
        if (TablaProductos.getColumnModel().getColumnCount() > 0) {
            TablaProductos.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(1).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(2).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(3).setPreferredWidth(30);
            TablaProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            TablaProductos.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-x.png"))); // NOI18N

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-proyecto.png"))); // NOI18N

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar-codigo.png"))); // NOI18N
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
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
                            .addComponent(jButton18)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton16)
                                .addGap(18, 18, 18)
                                .addComponent(jButton17)
                                .addGap(18, 18, 18)
                                .addComponent(jButton20)))
                        .addGap(529, 529, 529))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(JLabelNombreProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtIdProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2))
                                            .addComponent(TxtNombreProducto)
                                            .addComponent(txtDescripcionProductos))))
                                .addGap(16, 16, 16)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton17)
                    .addComponent(jButton20)
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addComponent(jButton18)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Productos", jPanel3);
        jPanel3.getAccessibleContext().setAccessibleParent(jScrollPane4);

        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cliente", "Vendedor", "Total"
            }
        ));
        jScrollPane5.setViewportView(TablaVentas);
        if (TablaVentas.getColumnModel().getColumnCount() > 0) {
            TablaVentas.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaVentas.getColumnModel().getColumn(1).setPreferredWidth(60);
            TablaVentas.getColumnModel().getColumn(2).setPreferredWidth(60);
            TablaVentas.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnImprimirPDF)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnImprimirPDF)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ventas", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setText("RUC");

        jLabel27.setText("Nombre");

        jLabel28.setText("Direccion");

        jLabel29.setText("Telefono");

        jLabel30.setText("Razon Social");

        jButton23.setText("Actualizar");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton23)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField23)
                            .addComponent(jTextField25)
                            .addComponent(jTextField21)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel30))
                                .addGap(0, 32, Short.MAX_VALUE)))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jTextField22, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(jTextField24))))
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
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton23)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Configuracion", jPanel5);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 790, 440));

        btnImprimirNuevaVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(btnImprimirNuevaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 60, 50));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total a Pagar :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, -1, -1));

        jLabel4.setText("---------");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 540, -1, -1));

        jLabel5.setText("DNI/RUC");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));

        jLabel6.setText("Nombre");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 40, -1));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 550, 130, -1));
        getContentPane().add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 550, 10, -1));

        txtDireccionClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionClienteVentaActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccionClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 10, -1));
        getContentPane().add(txtTelefonoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 10, -1));
        getContentPane().add(labelUsuarioRegistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 14, 250, 50));

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

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void txtDireccionClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionClienteVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteVentaActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sistemaventabd", "root", "4123");
            PreparedStatement pst = con.prepareStatement("INSERT INTO clientes (dni,nombre,telefono,direccion,razon) VALUES (?,?,?,?,?)");

            pst.setInt(1, Integer.parseInt(txtRucCliente.getText()));
            pst.setString(2, txtNombreCliente.getText());
            pst.setInt(3, Integer.parseInt(txtTelefonoCliente.getText()));
            pst.setString(4, txtDireccionCliente.getText());
            pst.setString(5, txtRazonSocialCliente.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Cliente Registrado correctamente");
            Limpiar();
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
            ProductosDAO productDao = new ProductosDAO();

            PreparedStatement pst = productDao.CrearProducto();
            /*
            INSERT INTO productos(nombre,cantidad ,precio,descripcion,codigo,proveedor_id )
VALUES ("Huevos",180,27.5,"Carton de huevos", 0002,2);

             */
            Proveedores seleccionado = (Proveedores) jComboBox1.getSelectedItem();

            pst.setString(1, TxtNombreProducto.getText());
            pst.setInt(2, Integer.parseInt(txtCantidadProductos.getText()));
            pst.setInt(3, Integer.parseInt(txtPrecioProductos.getText()));
            pst.setString(4, txtDescripcionProductos.getText());
            pst.setString(5, txtCodigoProducto.getText());
            pst.setInt(6, seleccionado.getId());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Producto creado.");

        } catch (SQLException e) {
            System.out.println("Error creando productos");
        }
        VerProductos();

    }//GEN-LAST:event_jButton16ActionPerformed

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
            java.util.logging.Logger.getLogger(SistemaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SistemaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel JLabelNombreProducto;
    private javax.swing.JTable JTableNuevaVenta;
    private javax.swing.JScrollPane ScrollPaneProveedores;
    private javax.swing.JScrollPane ScrollPaneTablaCliente;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTable TablaProveedores;
    private javax.swing.JTable TablaVentas;
    private javax.swing.JTextField TxtNombreProducto;
    private javax.swing.JButton btnEditarCliente;
    private javax.swing.JButton btnEditarProveedor;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarProveedor;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JButton btnImprimirNuevaVenta;
    private javax.swing.JButton btnImprimirPDF;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<Proveedores> jComboBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JLabel labelUsuarioRegistrado;
    private javax.swing.JTextField txtCantidadProductos;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDescripcionProductos;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdProductos;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioProductos;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtRazonSocialCliente;
    private javax.swing.JTextField txtRazonSocialProveedor;
    private javax.swing.JTextField txtRucCliente;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoProveedor;
    private javax.swing.JTextField txtTelefonoVenta;
    // End of variables declaration//GEN-END:variables
}
