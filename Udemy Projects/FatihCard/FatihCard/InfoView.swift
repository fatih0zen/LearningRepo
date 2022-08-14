//
//  InfoView.swift
//  FatihCard
//
//  Created by Fatih Ozen on 10/4/21.
//

import SwiftUI

struct InfoView: View {
    let layerText: String
    let textColor: Color
    let imageText: String
    
    var body: some View {
        RoundedRectangle(cornerRadius: 20)
            .fill(Color.white)
            .frame(height: 50)
            .overlay(HStack {
                Image(systemName: imageText).foregroundColor(textColor)
                Text(layerText)
            })
            .padding(/*@START_MENU_TOKEN@*/.all/*@END_MENU_TOKEN@*/)
    }
}

struct InfoView_Previews: PreviewProvider {
    static var previews: some View {
        InfoView(layerText: "Hello", textColor: Color.green, imageText: "phone.fill")
            .previewLayout(.sizeThatFits)
    }
}
