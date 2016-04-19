var image = new SimpleImage(200, 200);
print(image);
for(var p of image.values()){
    var x = p.getX();
    var y = p.getY();
    if(x < 100){
        p.setRed(255);
        if(y > 100){
            p.setBlue(255);
        }
    }else{
        if(y < 100){
            p.setGreen(255);
        }
        else{
            p.setBlue(255);
        }       
    }   
}
print(image);

var image = new SimpleImage(200, 200);
print(image);
var w = image.getWidth();
for(var p of image.values()){
    var x = p.getX();
    var y = p.getY();
    if(y < w / 3){p.setRed(255);}
    else{
        if(y < 2 * w / 3){
            if(x < 70){p.setRed(255);}
            else{p.setGreen(255);}
        }else{p.setBlue(255);}
    }
}

print(image);

