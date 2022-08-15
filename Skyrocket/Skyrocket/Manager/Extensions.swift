//
//  Extensions.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/5/21.
//

import UIKit
import SwiftUI

//MARK: - Numberformatter
extension NumberFormatter {
    static let percentFormatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.locale = .current
        formatter.numberStyle = .percent
        formatter.maximumFractionDigits = 2
        return formatter
    }()
    
    static let numberFormatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.locale = .current
        formatter.numberStyle = .decimal
        formatter.maximumFractionDigits = 2
        return formatter
    }()
}

//MARK: - UIImageView
extension UIImageView {
    func setImage(with url: URL?) {
        guard let url = url else {return}
        
        DispatchQueue.global(qos: .userInteractive).async {
            let task = URLSession.shared.dataTask(with: url) { [weak self] data, _, error in
                guard let data = data, error == nil else {return }
                DispatchQueue.main.async {
                    self?.image = UIImage(data: data)
                }
            }
            task.resume()
        }
    }
}

//MARK: - Color
extension Color {
    static let darkBackground = Color(red: 0 / 255, green: 0 / 255, blue: 0 / 255)
    static let lightBackground = Color(red: 217 / 255, green: 231 / 255, blue: 245 / 255)
}
