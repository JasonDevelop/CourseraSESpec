var fg = new SimpleImage("drewRobert.png");
//print(fg);
var width = fg.getWidth();
var height = fg.getHeight();
var bg = new SimpleImage("dinos.png");
//print(bg);
var out = new SimpleImage(width, height);
//print(out);
for(var op of out.values()){
    var x = op.getX();
    var y = op.getY();
    var fp = fg.getPixel(x, y);
    var bp = bg.getPixel(x, y);
    if(fp.getGreen() > fp.getRed() + fp.getBlue()){ // fp green
        out.setPixel(x, y, bp);
    }else{
        out.setPixel(x, y, fp);
    }
}
print(out);