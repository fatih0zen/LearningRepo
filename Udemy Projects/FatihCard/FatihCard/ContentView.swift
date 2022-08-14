//
//  ContentView.swift
//  FatihCard
//
//  Created by Fatih Ozen on 10/4/21.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        ZStack {
            Color(red: 0.24, green: 0.24, blue: 0.24)
                .edgesIgnoringSafeArea(.all)
            VStack {
                Image("Fatih")
                    .resizable()
                    .frame(width: 200, height: 300)
                    .aspectRatio(contentMode: .fit)
                    .clipShape(Circle())
                    .overlay(Circle().stroke(Color.white, lineWidth: 5))
                Text("Fatih Ozen")
                    .font(Font.custom("Ephesis-Regular", size: 60))
                    .fontWeight(.bold)
                    .foregroundColor(Color.white)
                Text("Software Engineer")
                    .font(.system(size: 20))
                    .foregroundColor(Color.white)
                Divider()
                InfoView(layerText: "+1 (412) 758 6004", textColor: Color.green, imageText: "phone.fill")
                InfoView(layerText: "fatih0zen@outlook.com", textColor: Color.yellow, imageText: "envelope.fill")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
