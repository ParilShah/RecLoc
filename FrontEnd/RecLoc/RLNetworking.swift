//
//  RLNetworking.swift
//  RecLoc
//
//  Created by Paril Shah on 5/1/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import Foundation
import Alamofire
import SwiftyJSON

class RLNetworking{

    func fetchResponseUsingGet(url:String, parameters:[String:Any]?,block: @escaping (JSON)->Void){
        Alamofire.request(url).responseJSON { response in
                switch response.result {
                    case .success:
                        block(JSON(data: response.data!))
                    break
                    case .failure(let error):
                        print(error)
            }
//            if let json = response.result.value {
//                print("JSON: \(json)")
//                block(JSON(data: response.data!))
//            }
        }
    }
    
    func fetchResponseUsingPost(url:String, parameters:[String:Any]?,block: @escaping (JSON)->Void){
        Alamofire.request(url, method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: nil).responseJSON {
                    response in
                    switch response.result {
                    case .success:
                        block(JSON(data: response.data!))
                        break
                    case .failure(let error):
                        print(error)
            }
        }
    }

}
