//
//  RLAlbumCollectionViewController.swift
//  RecLoc
//
//  Created by Paril Shah on 5/5/17.
//  Copyright © 2017 Paril Shah. All rights reserved.
//

import UIKit
import DZNEmptyDataSet
import NVActivityIndicatorView

private let reuseIdentifier = "PlacesCell"

class RLAlbumCollectionViewController: UICollectionViewController,DZNEmptyDataSetSource, DZNEmptyDataSetDelegate {
    var items = [Any]()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Uncomment the following line to preserve selection between presentations
         self.clearsSelectionOnViewWillAppear = false

        // Register cell classes
//        self.collectionView!.register(UICollectionViewCell.self, forCellWithReuseIdentifier: reuseIdentifier)

        // Do any additional setup after loading the view.
        let size: CGFloat = (self.collectionView!.frame.size.width - 5) / 2.0
        let cellSize = CGSize(width:size, height:size)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 1, left: 1, bottom: 1, right: 1)
        layout.minimumLineSpacing = 1.0
        layout.minimumInteritemSpacing = 1.0
        self.collectionView?.setCollectionViewLayout(layout, animated: true)
        
        self.collectionView!.emptyDataSetSource = self
        self.collectionView!.emptyDataSetDelegate = self
        
        fetchUserLocations()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: CUSTOM METHODS
    func fetchUserLocations(){
        let points = CGPoint(x: UIScreen.main.bounds.width/2.0 - 25, y: UIScreen.main.bounds.height/2.0 - 25)
        let activityIndicator =  NVActivityIndicatorView(frame: CGRect(x: points.x, y: points.y, width: 50, height: 50), type: .ballScaleMultiple, color: UIColor.init(colorLiteralRed: 216.0/255.0, green: 67.0/255.0, blue: 21.0/255.0, alpha: 1.0), padding: NVActivityIndicatorView.DEFAULT_PADDING)
        self.view.addSubview(activityIndicator)
        activityIndicator.startAnimating()
        
        let rlPlacesVM = RLPlacesVM.init(urlString:"http://localhost:8080/user/fetchUserLocations", paramerts:nil, block:{(response:AnyObject)in
            self.items = response as! [Location]
            self.collectionView?.reloadData()
            activityIndicator.stopAnimating()
        })
        rlPlacesVM.fetchUserLocations()
    }
    
    // MARK: - DZNEmptyDataSetSource
    func title(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "No Photos"
        let attributes = [
            NSFontAttributeName: UIFont.boldSystemFont(ofSize: 18.0)
        ]
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func description(forEmptyDataSet scrollView: UIScrollView!) -> NSAttributedString! {
        let text = "Get started by uploading a photo."
        let paragraph = NSMutableParagraphStyle()
        paragraph.lineBreakMode = .byWordWrapping
        paragraph.alignment = .center
        
        let attributes = [
            NSFontAttributeName: UIFont.systemFont(ofSize: 14.0),
            NSParagraphStyleAttributeName: paragraph
        ]
        
        return NSAttributedString(string: text, attributes: attributes)
    }
    
    func image(forEmptyDataSet scrollView: UIScrollView!) -> UIImage! {
        return UIImage(named: "placeholder_photo")
    }
    
    func backgroundColor(forEmptyDataSet scrollView: UIScrollView!) -> UIColor! {
        return UIColor.init(red: 236.0/255.0, green: 240.0/255.0, blue: 241.0/255.0, alpha: 1.0)
    }

    /*
     // MARK: - Navigation
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using [segue destinationViewController].
     // Pass the selected object to the new view controller.
     }
     */
}

extension RLAlbumCollectionViewController {
    
    // MARK: UICollectionViewDataSource
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int
    {
        return self.items.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! RLPlaceCollectionViewCell
        // Configure the cell
        let location = items[indexPath.row] as! Location
        //        cell.imageView?.image = UIImage(named:(dictionary["ImageName"] as? String)!)
        cell.textLabel?.text = location.locationName
        return cell
    }
    
    // MARK: UICollectionViewDelegateFlowLayout
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let retval = CGSize(width:(self.collectionView?.frame.size.width)!/2, height:64);
        return retval;
    }
    
    
    // MARK: UICollectionViewDelegate
    
    /*
     // Uncomment this method to specify if the specified item should be highlighted during tracking
     override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
     return true
     }
     */
    
    /*
     // Uncomment this method to specify if the specified item should be selected
     override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
     return true
     }
     */
    
    /*
     // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
     override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
     return false
     }
     
     override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
     return false
     }
     
     override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
     
     }
     */

}
