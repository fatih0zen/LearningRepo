//
//  HistoricalPriceData.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/9/21.
//

import Foundation

struct HistoricalPriceData: Codable {
    let data: [Data]
}

struct Data: Codable {
    let open: Double
    let high: Double
    let low: Double
    let close: Double
    let volume: Int
    let date: String
}
