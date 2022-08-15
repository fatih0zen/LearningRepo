//
//  HomeView.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/21/21.
//

import SwiftUI

struct HomeView: View {
    @ObservedObject var viewModel: SkyrocketViewModel
    //var stockSymbol = "AAPL"
    
    var body: some View {
        NavigationView {
            //UISearchBar
            NavigationLink("GO", destination: StockView(viewModel: viewModel))
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        let viewModel = SkyrocketViewModel()
        HomeView(viewModel: viewModel)
    }
}
