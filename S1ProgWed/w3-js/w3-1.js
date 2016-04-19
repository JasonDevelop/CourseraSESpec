var image = new SimpleImage(200,200);
print(image);
for(var p of image.values()){
    var x = p.getX();
    var y = p.getY();
    if(x - 20 < 0){
        if(y - x > 0){
            if(y + x -200 < 0 ){
                p.setRed(255);
            }
        }
    }
}
print(image);