//
//  StockFinancials.swift
//  Skyrocket
//
//  Created by Fatih Ozen on 10/3/21.
//

import Foundation

struct StockFinancials: Codable {
    let results : [FinancialsResults]
}

struct FinancialsResults: Codable {
    let financials : Financials
    let start_date : String
    let end_date : String
    let company_name : String
    let fiscal_period : String
    let fiscal_year : String
}

//MARK: - Financials
// -> IncomeStatement, BalanceSheet, CashFlowStatement, and ComprehensiveIncome
struct Financials: Codable {
    let income_statement: IncomeStatement
    let balance_sheet: BalanceSheet
    let cash_flow_statement: CashFlowStatement
    let comprehensive_income: ComprehensiveIncome
}

//MARK: - IncomeStatement
struct IncomeStatement: Codable {
    let revenues : FinancialsStructure
    let basic_earnings_per_share : FinancialsStructure
    let income_loss_from_continuing_operations_after_tax : FinancialsStructure
    let benefits_costs_expenses : FinancialsStructure
    let gross_profit : FinancialsStructure
    let participating_securities_distributed_and_undistributed_earnings_loss_basic : FinancialsStructure
    let nonoperating_income_loss : FinancialsStructure
    let net_income_loss : FinancialsStructure
    let income_loss_from_continuing_operations_before_tax : FinancialsStructure
    let operating_income_loss : FinancialsStructure
    let income_tax_expense_benefit : FinancialsStructure
    let net_income_loss_attributable_to_noncontrolling_interest : FinancialsStructure
    let preferred_stock_dividends_and_other_adjustments : FinancialsStructure
    let net_income_loss_attributable_to_parent : FinancialsStructure
    let interest_expense_operating : FinancialsStructure
    let costs_and_expenses : FinancialsStructure
    let operating_expenses : FinancialsStructure
    let cost_of_revenue : FinancialsStructure
    let diluted_earnings_per_share : FinancialsStructure
    let net_income_loss_available_to_common_stockholders_basic : FinancialsStructure
}

//MARK: - BalanceSheet
struct BalanceSheet: Codable {
    let liabilities: FinancialsStructure
    let liabilities_and_equity: FinancialsStructure
    let noncurrent_assets: FinancialsStructure
    let noncurrent_liabilities: FinancialsStructure
    let equity_attributable_to_noncontrolling_interest: FinancialsStructure
    let assets: FinancialsStructure
    let equity_attributable_to_parent: FinancialsStructure
    let current_liabilities: FinancialsStructure
    let current_assets: FinancialsStructure
    let equity: FinancialsStructure
}

//MARK: - CashFlowStatement
struct CashFlowStatement: Codable {
    let exchange_gains_losses: FinancialsStructure
    let net_cash_flow: FinancialsStructure
    let net_cash_flow_from_financing_activities: FinancialsStructure
}

//MARK: - ComprehensiveIncome
struct ComprehensiveIncome: Codable {
    let comprehensive_income_loss_attributable_to_parent: FinancialsStructure
    let other_comprehensive_income_loss: FinancialsStructure
    let comprehensive_income_loss: FinancialsStructure
    let comprehensive_income_loss_attributable_to_noncontrolling_interest: FinancialsStructure
}

struct FinancialsStructure: Codable {
    let label: String
    let value: Double
    let unit: String
    let order: Int
}
