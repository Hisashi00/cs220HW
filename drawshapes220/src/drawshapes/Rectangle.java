package drawshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends AbstractShape
{
    protected int width;
    protected int height;
    
    public Rectangle(Point center, int width, int height, Color color){
        super(center); 
        this.width = width;
        this.height = height;
        this.color = color;
        setBoundingBox(center.x - width / 2, center.x + width / 2,
                       center.y - height / 2, center.y + height / 2);
    }
    
    
    public Rectangle(int left, int right, int top, int bottom) {
        super(new Point(left + (right- left)/2 , top+ (bottom -top)/2));
        setBoundingBox(left, right, top, bottom);
        this.color = Color.BLUE;
        this.width = right - left;
        this.height = bottom - top;
    }

    /* (non-Javadoc)
     * @see drawshapes.sol.Shape#draw(java.awt.Graphics)
     */
    @Override
    public void draw(Graphics g) {
        if (isSelected()){
            g.setColor(color.darker());
        } else {
            g.setColor(getColor());
        }
        g.fillRect(anchorPoint.x - width / 2, anchorPoint.y - height / 2, width, height);

    }

    public String toString() {
        return String.format("RECTANGLE %d %d %d %d %s %s", 
                getAnchorPoint().x,
                getAnchorPoint().y,
                width,
                height,
                colorToString(getColor()),
                selected);
    }
    

    /* (non-Javadoc)
     * @see drawshapes.sol.Shape#setAnchorPoint(java.awt.Point)
     */
    @Override
    public void setAnchorPoint(Point p) {
        this.anchorPoint = p;
        setBoundingBox(p.x - width / 2, p.x + width / 2,
                       p.y - height / 2, p.y + height / 2);
    }
    


    public IShape copy(){
        return new Rectangle(new Point(anchorPoint.x, anchorPoint.y), width, height, color);
    }

    @Override
    public void scaleUp() {
        width = (int)(width * 1.25);
        height = (int)(height * 1.25);
        setBoundingBox(anchorPoint.x - width / 2, anchorPoint.x + width / 2,
                       anchorPoint.y - height / 2, anchorPoint.y + height / 2);
    }
    
    @Override
    public void scaleDown() {
        width = (int)(width * 0.8);
        height = (int)(height * 0.8);
        setBoundingBox(anchorPoint.x - width / 2, anchorPoint.x + width / 2,
        anchorPoint.y - height / 2, anchorPoint.y + height / 2);
        
    }

    @Override
public final void move(int dx, int dy){
        anchorPoint.translate(dx, dy);
    setBoundingBox(anchorPoint.x - width / 2, anchorPoint.x + width / 2,
                   anchorPoint.y - height / 2, anchorPoint.y + height / 2);
}

@Override
public double getArea() {
    return width * height;
}

    
    
}
