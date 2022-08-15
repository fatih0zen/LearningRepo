//
//  SearchResponse.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/3/21.
//

import Foundation

struct SearchResponse: Codable {
    let result: [SearchResults]
}

struct SearchResults: Codable {
    let description: String
    let displaySymbol: String
    let symbol: String
    let type: String
}
