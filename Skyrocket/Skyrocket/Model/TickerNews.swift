//
//  TickerNews.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 10/8/21.
//

import Foundation

struct TickerNews: Codable {
    let results : [newsResults]
}

struct newsResults: Codable {
    let id : String?
    let publisher : Publisher
    let title : String?
    let author : String?
    let published_utc : String?
    let article_url : String?
    let tickers : [String]?
    let amp_url : String?
    let image_url : String?
    let description : String?
    let keywords : [String]?
}

struct Publisher: Codable {
    let name : String?
    let homepage_url : String?
    let logo_url : String?
    let favicon_url : String?
}
