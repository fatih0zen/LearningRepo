//
//  ContentView.swift
//  What The Hack
//
//  Created by Fatih Ozen on 10/5/21.
//

import SwiftUI

struct ContentView: View {
    
    @ObservedObject var networkManager = NetworkManager()
    
    var body: some View {
        NavigationView {
            List(networkManager.posts) { post in
                NavigationLink(destination: DetailView(url: post.url)) {
                    HStack {
                        Text(String(post.points))
                        Text(post.title).padding()
                    }
                }
            }
            .navigationBarTitle("What The Hack?")
            .padding(.top, 30)
        }
        .onAppear {
            self.networkManager.fetchData()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

//let posts = [
//    Post(id: "1", title: "Hello"),
//    Post(id: "2", title: "Merhaba"),
//    Post(id: "3", title: "Selam")
//]
