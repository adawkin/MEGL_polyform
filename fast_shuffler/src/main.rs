use core::option::Iter;
use std::collections::BTreeSet;
use std::rc::Rc;
use std::{collections::{HashMap, LinkedList}, env::args};

//TODO: alignment of pieces

#[derive(Debug, Eq, Hash, PartialEq, Clone, PartialOrd, Ord)]
struct Piece(i32,i32,i32);

type PolyominoList = LinkedList<Piece>;
// TODO: switch to Arc/Rc
type StrongGraph<'a> = HashMap<Rc<Piece>,Vec<Rc<Piece>>>;

fn insert_graph(graph: &mut StrongGraph, piece: Piece) {
    // check if the piece is in the graph
    let piece = Rc::new(piece);

    let neighbor_candidates = vec![
        Piece(piece.0+1, piece.1, piece.2),
        Piece(piece.0-1, piece.1, piece.2),

        Piece(piece.0, piece.1+1, piece.2),
        Piece(piece.0, piece.1-1, piece.2),

        Piece(piece.0, piece.1, piece.2+1),
        Piece(piece.0, piece.1, piece.2-1)
    ];

    let mut actual_neighbors = Vec::new();

    for i in 0..neighbor_candidates.len() {
        match graph.get_key_value(&neighbor_candidates[i]) {
            Some((key, _)) => {
                actual_neighbors.push(Rc::clone(key));
            },
            None => ()
        }
    }

    graph.insert(Rc::clone(&piece), actual_neighbors);

    for candidate in neighbor_candidates {
        match graph.get_mut(&candidate) {
            Some(neighbors_neighbors) => {
                neighbors_neighbors.push(Rc::clone(&piece));
            },
            None => ()
        }
    }

}


fn remove_graph(graph: &mut StrongGraph, piece: &Rc<Piece>) {
    match graph.remove(piece) {
        Some(mut neighbors) => {
            for neighbor in neighbors.iter_mut() {
                // remove the piece from all pieces that point to it
                let neighbors_neighbors = graph.get_mut(neighbor).expect("an elements neighbors must be in the graph");
                let position = neighbors_neighbors.into_iter().position(|x| x == piece).expect("elements neighbors must contain element (undirected graph)");
                neighbors_neighbors.swap_remove(position);
            }
        },
        None => ()
    }
}


fn initialize_graph(graph: &mut StrongGraph, size: i32) {
    for i in 0..size {
        let piece = Piece(0,0,i);
        insert_graph(graph, piece);
    }
}

fn get_perimeter(graph: &mut StrongGraph) -> BTreeSet<Piece> {
    let perimeter: BTreeSet<Piece> = BTreeSet::new();
    for key in graph.keys() {
        if !graph.contains_key(key) {
        }
    };

    perimeter
}

fn main() {
    let mut args = args();
    args.next();
    let size = i32::from_str_radix(&args.next().expect("must provide size argument"),10).expect("must provide base 10 integer size argument");
    let mut mino = StrongGraph::new();
    initialize_graph(&mut mino, size);

    // pick a random 
    remove_graph(&mut mino, &to_remove);
    println!("{:?}", mino);
}
