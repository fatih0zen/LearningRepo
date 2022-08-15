//
//  StockQuote.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 10/22/21.
//

import Foundation

struct StockQuote: Codable {
    let avgTotalVolume: Double?
    let change: Double?
    let changePercent: Double?
    //let close: Double?
    let delayedPrice: Double?
    let extendedPrice: Double?
    //let high: Double?
    let latestPrice: Double?
    //let low: Double?
    let marketCap: Double?
    let peRatio: Double?
    let volume: Double?
    let week52High: Double?
    let week52Low: Double?
    let isUSMarketOpen: Bool
}
