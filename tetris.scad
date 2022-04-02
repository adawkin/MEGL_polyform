module bcube() {
    difference() {
        cube([10, 10, 10], center=true);
        
        translate([5,5,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([5,-5,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([-5,5,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([-5,-5,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([0,5,5]) rotate([0,90,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([0,-5,5]) rotate([0,90,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([0,5,-5]) rotate([0,90,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([0,-5,-5]) rotate([0,90,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([-5,0,-5]) rotate([90,0,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([5,0,-5]) rotate([90,0,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([-5,0,5]) rotate([90,0,0]) rotate([0,0,45]) cube([2,2,11], center=true);
        
        translate([5,0,5]) rotate([90,0,0]) rotate([0,0,45]) cube([2,2,11], center=true);
    }
}

translate([12,0,0]) {
bcube();
translate([0,10,0]) bcube();
translate([0,20,0]) bcube();
translate([10,0,0]) bcube();
}

translate([20,-12,0]) rotate([0, 0, 90]) {
bcube();
translate([0,10,0]) bcube();
translate([0,20,0]) bcube();
translate([0, 30,0]) bcube();
}

translate([35,3,0]) {
bcube();
translate([0,10,0]) bcube();
translate([-10,10,0]) bcube();
translate([0, 20,0]) bcube();
}

bcube();
translate([0,10,0]) bcube();
translate([-10,10,0]) bcube();
translate([-10, 20,0]) bcube();