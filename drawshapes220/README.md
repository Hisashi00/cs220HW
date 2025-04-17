# 🖼️ DrawShapes Application

This is a simple Java Swing application that allows users to draw and manipulate shapes interactively.  
It includes basic shape creation and color selection, as well as more complex operations like movement, scaling, saving/loading, and undo.

---

## ✅ Basic Features

### 🟦 Add Rectangles and Other Shapes
- Select **Rectangle**, **Square**, or **Circle** from the **"Shape"** menu.
- Left-click on the canvas to add the selected shape.

### 🎨 Add New Colors
- Choose from **Red**, **Blue**, **Yellow**, **Green**, or **Magenta** using the **"Color"** menu.
- The selected color will apply to any new shape created.

---

## 🔁 Complex Features

### 🔀 Move Shapes
- Right-click a shape to select it.
- Use the `W`, `A`, `S`, `D` keys to move the selected shape(s):
  - `W`: up  
  - `A`: left  
  - `S`: down  
  - `D`: right

### 🔍 Scale Shapes
- Press `=` to scale up selected shape(s) by 1.25×.
- Press `-` to scale down selected shape(s) by 0.8×.

---

## 💾 File Operations

### 💾 Save Scene
- Use **File → Save** to export the current scene to a text file.
- Each shape's type, position, size, color, and selection state is saved.

### 📂 Load Scene
- Use **File → Load** to import a previously saved scene.

---

## ↩️ Undo

- Press `Z` to undo the most recent operation.
- Supports undo for **add**, **move**, **scale**, and **load**.

---

## ✨ Two Extra Features

### 1. 🎲 Randomize Positions
- Use **Operation → Randomize Positions** to scatter all shapes to random locations on the canvas.

### 2. 📏 Sort Shapes by Size and Arrange in Grid
- Use **Operation → Sort by Size** to:
  - Sort shapes by area (smallest to largest),
  - Automatically arrange them in a **grid layout** that fits within the canvas dimensions.

---

## ▶️ How to Run

Compile and run using your IDE or from the terminal:

```bash
javac drawshapes/*.java
java drawshapes.DrawShapes
