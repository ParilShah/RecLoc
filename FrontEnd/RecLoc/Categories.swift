//
//  Categories.swift
//  RecLoc
//
//  Created by Paril Shah on 5/2/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import SwiftyJSON

struct Categories {
    var categoryName:String?
    var categoryId:Int?
    
    init(jsonObject:JSON) {
        categoryName = jsonObject["categoryName"].string
        categoryId = jsonObject["categoryId"].int
    }
}
