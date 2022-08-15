//
//  TickerDetails.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 10/3/21.
//

import Foundation

struct TickerDetails: Codable {
    //let logo: String?
    let symbol: String?
    let companyName: String?
    let exchange: String?
    let industry: String?
    let website: String?
    let description: String?
    let CEO: String?
    let securityName: String?
    let issueType: String?
    let sector: String?
    let primarySicCode: Int?
    let employees: Int?
    let tags: [String]?
    let address: String?
    let address2: String?
    let state: String?
    let city: String?
    let zip: String?
    let country: String?
    let phone: String?
}
