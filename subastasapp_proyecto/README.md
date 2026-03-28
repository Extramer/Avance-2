# Plataforma Digital de Subastas Especializadas
## Avance 2 - Programación Orientada a Objetos - CENFOTEC

---

## Estructura del proyecto

Este entregable contiene **dos proyectos separados**, según la arquitectura por capas del curso:

```
logica-negocio/          ← Proyecto 1: Capa lógica de negocio
  src/
    logica/
      dominio/           ← Clases del modelo de dominio
        Usuario.java       (abstracta - clase base)
        Moderador.java     (hereda de Usuario)
        Vendedor.java      (hereda de Usuario)
        Coleccionista.java (hereda de Usuario)
        Objeto.java
        Oferta.java
        Subasta.java
        OrdenAdjudicacion.java
      gestor/            ← Capa de lógica y reglas de negocio
        Gestor.java

ui-consola/              ← Proyecto 2: Interfaz gráfica (consola)
  src/
    ui/
      Main.java          ← Menú principal (NO instancia objetos de negocio)
```

---

## Arquitectura implementada

- **Capa lógica** (`logica-negocio`): Contiene todas las clases del dominio y el Gestor.
- **Capa UI** (`ui-consola`): Contiene únicamente el Main con el menú de consola.
  - La UI **nunca instancia objetos de dominio directamente** — toda la lógica pasa por el Gestor.

---

## Reglas de negocio implementadas

1. Al iniciar la app, verifica si existe un moderador; si no, solicita su registro.
2. Solo puede existir un único moderador.
3. El moderador no puede crear subastas ni realizar ofertas.
4. Los vendedores no pueden realizar ofertas.
5. Un coleccionista no puede ofertar en su propia subasta.
6. No se puede crear una subasta sin objetos asociados.
7. Vendedores y coleccionistas deben ser mayores de edad.
8. El moderador debe ser mayor de edad.
9. Un coleccionista solo puede subastar objetos de su colección.

---

## Menú de consola (opciones)

1. Registro de usuarios (coleccionista / vendedor)
2. Listado de usuarios
3. Creación de subastas
4. Listado de subastas
5. Creación de ofertas
6. Listado de ofertas
7. **Inicio de sesión** ← NUEVO en Avance 2
8. **Validar existencia de moderador** ← NUEVO en Avance 2
9. Cerrar sesión
0. Salir

---

## Cómo compilar y ejecutar

### Compilar la capa lógica primero:
```bash
cd logica-negocio/src
javac -d ../../logica-out logica/dominio/*.java logica/gestor/Gestor.java
```

### Compilar la UI (referenciando la capa lógica):
```bash
cd ui-consola/src
javac -cp ../../logica-out -d ../../ui-out ui/Main.java
```

### Ejecutar:
```bash
java -cp logica-out:ui-out ui.Main
# En Windows: java -cp logica-out;ui-out ui.Main
```

---

## Herencia implementada

```
Usuario (abstracta)
├── Moderador
├── Vendedor
└── Coleccionista
```

---

## Novedades del Avance 2

- ✅ Método `equals()` en **todas** las clases del proyecto
- ✅ Inicio de sesión con validación de credenciales
- ✅ Validación de existencia del moderador al iniciar la app
- ✅ Clase `Vendedor` completa (faltaba en avance 1)
- ✅ Clase `Objeto` con cálculo de antigüedad
- ✅ Clase `OrdenAdjudicacion` completa
- ✅ `Subasta` con tiempo restante calculado en días/horas/minutos/segundos
- ✅ `Usuario` con `LocalDate` para fecha de nacimiento y cálculo real de edad
- ✅ Todas las reglas de negocio validadas en el Gestor
