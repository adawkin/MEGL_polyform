use std::{collections::{HashMap, LinkedList}, env::args};

//TODO: alignment of pieces
//TODO: generating a StrongGraph

#[derive(Debug, Eq, Hash, PartialEq)]
struct Piece(i32,i32,i32);
type PolyominoList = LinkedList<Piece>;
type StrongGraph<'a> = HashMap<Piece,Vec<&'a Piece>>;

fn generate_graph(mino: &PolyominoList) -> StrongGraph {
    let mut graph = StrongGraph::new();
    for piece in mino {
        if !graph.contains_key(piece) {
            graph.insert(piece, Vec<&Piece>::new());
        }
        graph.get_mut(piece).expect("polyomino piece must have neighbors array by now");
        // add 
    }
    graph
}

fn align_to_zero(mino: &mut PolyominoList) {
    // find the lowest x, lowest y, and lowest z
    let mut lowest_x = i32::MAX;
    let mut lowest_y = i32::MAX;
    let mut lowest_z = i32::MAX;

    for piece in mino.iter_mut() {
        if piece.0 < lowest_x {
            lowest_x = piece.0;
        }
        if piece.1 < lowest_y {
            lowest_y = piece.1;
        }
        if piece.2 < lowest_z {
            lowest_z = piece.2;
        }
    }

    for piece in mino.iter_mut() {
        piece.0 -= lowest_x;
        piece.1 -= lowest_y;
        piece.2 -= lowest_z;
    }
}

fn generate_piece(n: i32) -> PolyominoList {
    let mut mino = PolyominoList::new();
    for i in 0..n {
       mino.push(Piece(2,3,i));
    }
    mino
}

fn main() {
    let mut args = args();
    args.next();
    let size = i32::from_str_radix(&args.next().expect("must provide size argument"),10).expect("must provide base 10 integer size argument");
    let mut mino = generate_piece(size);
    println!("{:?}", mino);
    align_to_zero(&mut mino);
    println!("{:?}", mino);
}
