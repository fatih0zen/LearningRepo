//
//  SkyrocketViewModel.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 11/21/21.
//

import Foundation

class SkyrocketViewModel: ObservableObject {
    
    @Published var companyLogoUrl: String = ""
    @Published var companyTicker: String = "SKRT"
    @Published var companyName: String = "Skyrocket, Inc."
    
    @Published var stockPriceData = [8.0,23,54,32,12,37,7,23,43,8.0,230,54,32,12,37,7,23,43]
    @Published var stockPrice: String = "$150"
    
    @Published var description: String = "Description"
    @Published var ceo: String = "Fatih E. Ozen"
    @Published var industry: String = "Finance Technology"
    @Published var employeeNum: String = "154,000"
    @Published var hq: String = "Cupertino, CA"
    
    @Published var open: String = "148.96"
    @Published var close: String = "140.20"
    @Published var high: String = "149.08"
    @Published var low: String = "147.68"
    
    @Published var highIn52: String = "157.26"
    @Published var lowIn52: String = "112.59"
    @Published var volume: String = "40.86M"
    @Published var averageVol: String = "71.11M"
    @Published var marketCap: String = "2.43T"
    @Published var pToERatio: String = "26.35"
    @Published var dividend: String = "0.57"
    
    func search(_ companySymbol: String) {
        APIManager.shared.search(for: companySymbol) { result in
            switch result {
            case.success(let searchResults):
                DispatchQueue.main.async {
                    
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func getCompanyDetails(_ companySymbol: String) {
        APIManager.shared.details(for: companySymbol) { result in
            switch result {
            case.success(let details):
                DispatchQueue.main.async {
                    self.companyTicker = details.symbol ?? "-"
                    self.companyName = details.companyName ?? "-"
                    //SkyrocketViewModel.companyLogoUrl = details.logo ?? "-"
                    self.description = details.description ?? "-"
                    self.ceo = details.CEO ?? "-"
                    self.industry = details.industry ?? "-"
                    self.employeeNum = "\(details.employees ?? 0)"
                    self.hq = "\(details.city ?? "-"), \(details.state ?? "-")"
                }
            case .failure(let error):
                print(error)
            }

        }
    }
    
    func getCurrentPrice(_ companySymbol: String) {
        APIManager.shared.last(for: companySymbol) { result in
            switch result {
            case.success(let lastPrice):
                DispatchQueue.main.async {
                    self.stockPrice = "\(lastPrice)"
                }
            case .failure(let error):
                print(error)
            }
            
        }
    }
    
    func setUpLineChart(_ companySymbol: String) {
        APIManager.shared.marketData(for: companySymbol) { result in
            switch result {
            case .success(let response):
                DispatchQueue.main.async {
                    for i in response.open {
                        self.stockPriceData.append(Double(i))
                    }
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func getStockFinancials(_ companySymbol: String) {
        APIManager.shared.financials(for: companySymbol) { result in
            switch result {
            case .success(let financials):
                DispatchQueue.main.async {
                    
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func getMarketData(_ companySymbol: String) {
        APIManager.shared.marketData(for: companySymbol) { result in
            switch result {
            case .success(let marketData):
                DispatchQueue.main.async {
                    self.open = "\(marketData.open)"
                    self.close = "\(marketData.close)"
                    self.high = "\(marketData.high)"
                    self.low = "\(marketData.low)"
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func getStockQuote(_ companySymbol: String) {
        APIManager.shared.stockQuote(for: companySymbol) { result in
            switch result {
            case .success(let quote):
                DispatchQueue.main.async {
                    self.highIn52 = "\(quote.week52High ?? 0)"
                    self.lowIn52 = "\(quote.week52Low ?? 0)"
                    self.volume = "\(quote.volume ?? 0)"
                    self.averageVol = "\(quote.avgTotalVolume ?? 0)"
                    self.marketCap = "\(quote.marketCap ?? 0)"
                    self.pToERatio = "\(quote.peRatio ?? 0)"
                    //SkyrocketViewModel.dividend = "\()"
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    public func getNews() {
        APIManager.shared.getNews { result in
            switch result {
            case .success(let news):
                DispatchQueue.main.async {
                    
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    public func getNews(for companySymbol: String) {
        APIManager.shared.getNews(for: companySymbol) { result in
            switch result {
            case .success(let news):
                DispatchQueue.main.async {
                    
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    public func getNews(for companySymbol: String, limit: Int) {
        APIManager.shared.getNews(for: companySymbol, limit: limit) { result in
            switch result {
            case .success(let news):
                DispatchQueue.main.async {
                    
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
}
