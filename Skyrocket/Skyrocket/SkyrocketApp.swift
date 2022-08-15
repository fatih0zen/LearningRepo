//
//  SkyrocketApp.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/21/21.
//

import SwiftUI

@main
struct SkyrocketApp: App {
    private let viewModel = SkyrocketViewModel()
    
    var body: some Scene {
        WindowGroup {
            HomeView(viewModel: viewModel)
        }
    }
}
